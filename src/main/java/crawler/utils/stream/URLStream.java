package crawler.utils.stream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLStream extends GenericStream {

    @Override
    public InputStream getSource(String url) throws IOException {
        return new URL(url).openStream();
    }
}
