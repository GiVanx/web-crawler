package crawler.utils.stream;

import java.io.IOException;
import java.io.InputStream;

public abstract class GenericStream implements IStream {

    private InputStream inputStream;

    @Override
    public InputStream open(String url) throws IOException {
        close();
        this.inputStream = getSource(url);
        return this.inputStream;
    }

    @Override
    public void close() throws IOException {
        if (this.inputStream != null) {
            this.inputStream.close();
        }
    }

    public abstract InputStream getSource(String path) throws IOException;
}
