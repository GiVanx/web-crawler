package crawler;

import crawler.analyzer.ITechnologyAnalyzer;
import crawler.utils.html.IHTMLReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerWorker implements ICrawlerWorker {

    private String url;
    private IHTMLReader htmlReader;
    private ITechnologyAnalyzer technologyAnalyzer;
    public static final String SCRIPT_TAG_NAME = "script";
    public static final String SCRIPT_TAG_SRC_ATTRIBUTE_NAME = "src";

    public CrawlerWorker(String url, IHTMLReader htmlReader, ITechnologyAnalyzer technologyAnalyzer) {
        this.url = url;
        this.htmlReader = htmlReader;
        this.technologyAnalyzer = technologyAnalyzer;
    }

    @Override
    public Map<String, Integer> call() {
        try {
            this.htmlReader.init(url);

            Map<String, Integer> jsLibNameToCountMap = new HashMap<>();
            List<String> srcAttributes = htmlReader.getAttributes(SCRIPT_TAG_NAME, SCRIPT_TAG_SRC_ATTRIBUTE_NAME);

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
