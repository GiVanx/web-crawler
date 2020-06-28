package http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpService implements IHttpService {

    private ObjectMapper objectMapper;

    public HttpService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T get(String requestUrl, Class outputClass) throws IOException {

        URL url = new URL(requestUrl);

        StringBuilder response = new StringBuilder();
        int c;
        InputStreamReader streamReader = new InputStreamReader(url.openStream());
        while ((c = streamReader.read()) != -1) {
            response.append((char)c);
        }

        return (T)this.objectMapper.readValue(response.toString(), outputClass);
    }
}
