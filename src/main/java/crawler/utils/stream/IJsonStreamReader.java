package crawler.utils.stream;

import java.io.IOException;

public interface IJsonStreamReader {
    <T> T read(String path, Class outputClass) throws IOException;
}
