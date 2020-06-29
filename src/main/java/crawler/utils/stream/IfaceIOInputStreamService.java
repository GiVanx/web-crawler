package crawler.utils.stream;

import java.io.IOException;
import java.io.InputStream;

public interface IfaceIOInputStreamService {

    InputStream open(String path) throws IOException;

    void close() throws IOException;
}
