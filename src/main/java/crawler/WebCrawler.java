package crawler;

import google.IGoogleSearcher;
import google.model.GoogleSearchResult;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class WebCrawler {

    IGoogleSearcher googleSearcher;
    private static final int MAX_NUMBER_OF_WORKERS = 10;
    private static final int NUMBER_TOP_JS_LIBRARIES = 5;
    private ICrawlerWorkerFactory workerFactory;

    public WebCrawler(IGoogleSearcher googleSearcher, ICrawlerWorkerFactory workerFactory) {
        this.googleSearcher = googleSearcher;
        this.workerFactory = workerFactory;
    }

    public List<String> crawl(String searchTerm) throws InterruptedException, ExecutionException {

        GoogleSearchResult searchResult = googleSearcher.search(searchTerm);

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUMBER_OF_WORKERS);

        List<CrawlerWorker> workers = searchResult.getItems().stream().map(item -> workerFactory.create(item.getLink()))
                .collect(Collectors.toList());

        List<Future<Map<String, Integer>>> workerFutures = executorService.invokeAll(workers);

        Map<String, Integer> finalResult = gatherResults(workerFutures);

        System.out.println("Libraries count: " + finalResult);

        return getTopJsLibraryNames(finalResult);
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
