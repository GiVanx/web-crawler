package crawler.utils.html;

import java.io.IOException;
import java.util.List;

public interface IHTMLReader {

    List<String> getAttributes(String htmlTag, String attributeName);

    void init(String url) throws IOException;
}
