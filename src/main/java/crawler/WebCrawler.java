package crawler;

import crawler.factory.ICrawlerWorkerFactory;
import crawler.model.exceptions.CrawlerException;
import google.IGoogleSearcher;
import google.model.GoogleSearchResult;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WebCrawler {

    private IGoogleSearcher googleSearcher;
    private static final int MAX_NUMBER_OF_WORKERS = 10;
    private static final int NUMBER_TOP_JS_LIBRARIES = 5;
    private ICrawlerWorkerFactory workerFactory;
    private ExecutorService executorService;

    public WebCrawler(IGoogleSearcher googleSearcher, ICrawlerWorkerFactory workerFactory) {
        this.googleSearcher = googleSearcher;
        this.workerFactory = workerFactory;
        executorService = Executors.newFixedThreadPool(MAX_NUMBER_OF_WORKERS);
    }

    public List<String> crawl(String searchTerm) {

        GoogleSearchResult searchResult = googleSearcher.search(searchTerm);

        List<CrawlerWorker> workers = searchResult.getItems().stream().map(item -> workerFactory.create(item.getLink()))
                .collect(Collectors.toList());

        try {
            List<Future<Map<String, Integer>>> workerFutures = executorService.invokeAll(workers);

            Map<String, Integer> finalResult = gatherResults(workerFutures);
            return getTopJsLibraryNames(finalResult);

        } catch (Exception e) {
            System.err.println("[Web Crawler] Execution failed for search term '" + searchTerm + "'");
            throw new CrawlerException(e);
        }
    }

    public void stop() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }

    private List<String> getTopJsLibraryNames(Map<String, Integer> finalResult) {

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(finalResult.entrySet());

        Collections.sort(entries, Comparator.comparing(Map.Entry::getValue));
        List<String> topJsLibraryNames = new ArrayList<>();
        int i = entries.size() - 1;
        while ((entries.size() - i) <= NUMBER_TOP_JS_LIBRARIES && i >= 0) {
            topJsLibraryNames.add(entries.get(i).getKey());
            --i;
        }
        return topJsLibraryNames;
    }

    private Map<String, Integer> gatherResults(List<Future<Map<String, Integer>>> workerFutures) throws ExecutionException, InterruptedException {
        Map<String, Integer> finalResult = new HashMap<>();

        for (Future<Map<String, Integer>> future : workerFutures) {

            Map<String, Integer> jsLibraryNameToCount = future.get();

            for (Map.Entry<String, Integer> entry : jsLibraryNameToCount.entrySet()) {
                finalResult.put(entry.getKey(), finalResult.getOrDefault(entry.getKey(), 0) + jsLibraryNameToCount.get(entry.getKey()));
            }
        }
        return finalResult;
    }
}
