package json;

import java.io.IOException;
import java.io.InputStream;

public interface IJsonReader {

    <T> T read(InputStream inputStream, Class outputClass) throws IOException;
}
