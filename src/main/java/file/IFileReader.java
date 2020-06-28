package file;

import java.io.IOException;

public interface IFileReader {
    <T> T read(String filePath, Class outputClass) throws IOException;
}
