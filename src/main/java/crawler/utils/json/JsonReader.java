package crawler.utils.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonReader implements IJsonReader {
    private ObjectMapper objectMapper;

    public JsonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T read(InputStream inputStream, Class outputClass) throws IOException {

        StringBuilder response = new StringBuilder();
        int c;
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        while ((c = streamReader.read()) != -1) {
            response.append((char)c);
        }

        return (T)this.objectMapper.readValue(response.toString(), outputClass);
    }
}
