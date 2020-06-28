package crawler.utils;

import java.util.List;

public interface IURLReader {

    List<String> getAttribute(String htmlTag, String attributeName);
}
