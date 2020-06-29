package crawler.utils.file;

import java.io.IOException;

public interface IStreamReader {
    <T> T read(String path, Class outputClass) throws IOException;
}
