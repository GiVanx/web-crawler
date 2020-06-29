package crawler.utils.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileStreamMaker implements IOInputStreamMaker {

    @Override
    public InputStream getStream(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
}
