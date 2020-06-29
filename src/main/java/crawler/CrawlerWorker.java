package crawler;

import crawler.analyzer.ITechnologyAnalyzer;
import crawler.utils.http.IURLReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CrawlerWorker implements Callable<Map<String, Integer>> {

    private String url;
    private IURLReader urlReader;
    private ITechnologyAnalyzer technologyAnalyzer;
    private static final String SCRIPT_TAG_NAME = "script";
    private static final String SCRIPT_TAG_SRC_ATTRIBUTE_NAME = "src";

    public CrawlerWorker(String url, IURLReader urlReader, ITechnologyAnalyzer technologyAnalyzer) {
        this.url = url;
        this.urlReader = urlReader;
        this.technologyAnalyzer = technologyAnalyzer;
    }

    @Override
    public Map<String, Integer> call() {
        try {
            this.urlReader.init(url);

            Map<String, Integer> jsLibNameToCountMap = new HashMap<>();
            List<String> srcAttributes = urlReader.getAttributes(SCRIPT_TAG_NAME, SCRIPT_TAG_SRC_ATTRIBUTE_NAME);

            srcAttributes.forEach(attr -> {
                String libraryName = technologyAnalyzer.getJsLibraryName(attr);
                if (libraryName != null) {
                    jsLibNameToCountMap.putIfAbsent(libraryName, 0);
                    int newCount = jsLibNameToCountMap.get(libraryName) + 1;
                    jsLibNameToCountMap.put(libraryName, newCount);
                }
            });

            System.out.println("[Successful] " + url);

            return jsLibNameToCountMap;

        } catch (Exception exception) {
            System.err.println(String.format("[Failed][%s] %s", this.url, exception.getMessage()));
        }
        return new HashMap<>();
    }
}
