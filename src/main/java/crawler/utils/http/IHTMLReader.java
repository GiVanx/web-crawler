package crawler.utils.http;

import java.io.IOException;
import java.util.List;

public interface IHTMLReader {

    List<String> getAttributes(String htmlTag, String attributeName);

    void init(String url) throws IOException;
}
