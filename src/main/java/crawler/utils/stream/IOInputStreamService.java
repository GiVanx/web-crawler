package crawler.utils.stream;

import java.io.IOException;
import java.io.InputStream;

public class IOInputStreamService implements IfaceIOInputStreamService {

    private InputStream inputStream;
    private final IOInputStreamMaker ioInputStreamMaker;

    public IOInputStreamService(IOInputStreamMaker ioInputStreamMaker) {
        this.ioInputStreamMaker = ioInputStreamMaker;
    }

    @Override
    public InputStream open(String url) throws IOException {
        close();
        this.inputStream = ioInputStreamMaker.getStream(url);
        return this.inputStream;
    }

    @Override
    public void close() throws IOException {
        if (this.inputStream != null) {
            this.inputStream.close();
        }
    }
}
