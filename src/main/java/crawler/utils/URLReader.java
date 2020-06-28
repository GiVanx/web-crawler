package crawler.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class URLReader implements IURLReader {
    private String url;
    private Document document;

    public URLReader(String url) throws IOException {
        this.url = url;
        this.document = Jsoup.connect(url).get();
    }

    @Override
    public List<String> getAttribute(String htmlTag, String attributeName) {
        return document.select(htmlTag).stream().map(scriptTag -> scriptTag.attr(attributeName)).
                filter(srcAttribute -> !srcAttribute.isEmpty()).collect(Collectors.toList());
    }
}
