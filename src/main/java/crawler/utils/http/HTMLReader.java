package crawler.utils.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLReader implements IHTMLReader {
    private Document document;

    @Override
    public void init(String url) throws IOException {
        System.out.println("[Fetching] " + url);
        this.document = Jsoup.connect(url).get();
    }

    @Override
    public List<String> getAttributes(String htmlTag, String attributeName) {
        return document.select(htmlTag).stream().map(scriptTag -> scriptTag.attr(attributeName)).
                filter(srcAttribute -> !srcAttribute.isEmpty()).collect(Collectors.toList());
    }
}
