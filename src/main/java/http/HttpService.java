package http;

import json.IJsonReader;

import java.io.IOException;
import java.net.URL;

public class HttpService implements IHttpService {

    private IJsonReader jsonReader;

    public HttpService(IJsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public <T> T get(String requestUrl, Class outputClass) throws IOException {
        return this.jsonReader.read(new URL(requestUrl).openStream(), outputClass);
    }
}
