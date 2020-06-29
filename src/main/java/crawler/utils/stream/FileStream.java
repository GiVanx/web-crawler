package crawler.utils.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileStream extends GenericStream {

    @Override
    public InputStream getSource(String filePath) throws IOException {
        return new FileInputStream(filePath);
    }
}
