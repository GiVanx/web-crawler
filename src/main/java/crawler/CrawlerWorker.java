package crawler;

import analyzer.factory.ITechnologyAnalyzerFactory;
import crawler.utils.IURLReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CrawlerWorker implements Callable<Map<String, Integer>> {

    private String url;
    private IURLReader urlReader;
    private ITechnologyAnalyzerFactory technologyAnalyzerFactory;
    private static final String SCRIPT_TAG_NAME = "script";
    private static final String SCRIPT_TAG_SRC_ATTRIBUTE_NAME = "src";

    public CrawlerWorker(String url, IURLReader urlReader, ITechnologyAnalyzerFactory technologyAnalyzerFactory) {
        this.url = url;
        this.urlReader = urlReader;
        this.technologyAnalyzerFactory = technologyAnalyzerFactory;
    }

    @Override
    public Map<String, Integer> call() {
        try {
            this.urlReader.init(url);

            Map<String, Integer> jsLibNameToCountMap = new HashMap<>();
            List<String> srcAttributes = urlReader.getAttributes(SCRIPT_TAG_NAME, SCRIPT_TAG_SRC_ATTRIBUTE_NAME);

            srcAttributes.forEach(attr -> {
                String srcAttr = technologyAnalyzerFactory.getOrCreate().getTechnologyName(attr);
                jsLibNameToCountMap.putIfAbsent(srcAttr, 0);
                int newCount = jsLibNameToCountMap.get(srcAttr) + 1;
                jsLibNameToCountMap.put(srcAttr, newCount);
            });
            return jsLibNameToCountMap;

        } catch (Exception exception) {
            System.err.println(String.format("[Failed Worker][%s] %s", this.url, exception.getMessage()));
        }
        return new HashMap<>();
    }
}
