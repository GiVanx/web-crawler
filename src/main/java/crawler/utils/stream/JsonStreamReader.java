package crawler.utils.stream;

import crawler.utils.json.IJsonReader;

import java.io.*;

public class JsonStreamReader implements IJsonStreamReader {

    private IJsonReader jsonReader;
    private IfaceIOInputStreamService streamService;

    public JsonStreamReader(IJsonReader jsonReader, IfaceIOInputStreamService streamService) {
        this.jsonReader = jsonReader;
        this.streamService = streamService;
    }

    @Override
    public <T> T read(String path, Class outputClass) throws IOException {
        T output = jsonReader.read(streamService.open(path), outputClass);
        streamService.close();
        return output;
    }
}
