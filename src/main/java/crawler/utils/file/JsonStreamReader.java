package crawler.utils.file;

import crawler.utils.json.IJsonReader;
import crawler.utils.stream.IfaceIOInputStreamService;

import java.io.*;

public class JsonStreamReader implements IStreamReader {

    private IJsonReader jsonReader;
    private IfaceIOInputStreamService sourceStream;

    public JsonStreamReader(IJsonReader jsonReader, IfaceIOInputStreamService sourceStream) {
        this.jsonReader = jsonReader;
        this.sourceStream = sourceStream;
    }

    @Override
    public <T> T read(String path, Class outputClass) throws IOException {
        T output = jsonReader.read(sourceStream.open(path), outputClass);
        sourceStream.close();
        return output;
    }
}
