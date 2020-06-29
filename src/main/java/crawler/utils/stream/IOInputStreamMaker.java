package crawler.utils.stream;

import java.io.IOException;
import java.io.InputStream;

public interface IOInputStreamMaker {

    InputStream getStream(String path) throws IOException;
}
