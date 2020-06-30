package crawler.utils.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLReader implements IHTMLReader {
    private Document document;
    private final int REQUET_TIMEOUT = 2 * 1000; // 2 seconds

    @Override
    public void init(String url) throws IOException {
        this.document = Jsoup.connect(url).timeout(REQUET_TIMEOUT).get();
    }

    @Override
    public List<String> getAttributes(String htmlTag, String attributeName) {
        return document.select(htmlTag).stream().map(scriptTag -> scriptTag.attr(attributeName)).
                filter(srcAttribute -> !srcAttribute.isEmpty()).collect(Collectors.toList());
    }
}
