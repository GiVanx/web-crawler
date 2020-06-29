package crawler.utils.file;

import crawler.utils.json.IJsonReader;
import crawler.utils.stream.IStream;

import java.io.*;

public class JsonStreamReader implements IStreamReader {

    private IJsonReader jsonReader;
    private IStream sourceStream;

    public JsonStreamReader(IJsonReader jsonReader, IStream sourceStream) {
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
