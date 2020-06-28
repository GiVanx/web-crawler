package http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpService {

    public String get(String requestUrl) throws IOException {

        URL url = new URL(requestUrl);

        StringBuilder response = new StringBuilder();
        int c;
        InputStreamReader streamReader = new InputStreamReader(url.openStream());
        while ((c = streamReader.read()) != -1) {
            System.out.println(c);
            response.append((char)c);
        }
        return response.toString();
    }
}
