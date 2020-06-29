package crawler.utils.stream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLStreamMaker implements IOInputStreamMaker {

    @Override
    public InputStream getStream(String url) throws IOException{
        return new URL(url).openStream();
    }
}
