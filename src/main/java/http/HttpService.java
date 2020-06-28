package http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.IJsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
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
