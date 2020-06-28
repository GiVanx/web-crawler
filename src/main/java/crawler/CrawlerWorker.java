package crawler;

import crawler.utils.IScriptTagParser;
import crawler.utils.IURLReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CrawlerWorker implements Callable<Map<String, Integer>> {

    private String url;
    private IURLReader urlReader;
    private IScriptTagParser scriptTagParser;
    private static final String SCRIPT_TAG_NAME = "script";
    private static final String SCRIPT_TAG_SRC_ATTRIBUTE_NAME = "src";

    public CrawlerWorker(String url, IURLReader urlReader, IScriptTagParser scriptTagParser) {
        this.url = url;
        this.urlReader = urlReader;
        this.scriptTagParser = scriptTagParser;
    }

    @Override
    public Map<String, Integer> call() {

        Map<String, Integer> jsLibNameToCountMap = new HashMap<>();
        List<String> srcAttributes = urlReader.getAttributes(SCRIPT_TAG_NAME, SCRIPT_TAG_SRC_ATTRIBUTE_NAME);

        srcAttributes.forEach(attr -> {
            String srcAttr = scriptTagParser.getJsLibraryName(attr);
            jsLibNameToCountMap.putIfAbsent(srcAttr, 0);
            int newCount = jsLibNameToCountMap.get(srcAttr) + 1;
            jsLibNameToCountMap.put(srcAttr, newCount);
        });
        return jsLibNameToCountMap;
    }
}
