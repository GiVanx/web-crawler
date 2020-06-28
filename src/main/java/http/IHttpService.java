package http;

import java.io.IOException;

public interface IHttpService {

    <T> T get(String requestUrl, Class outputClass) throws IOException;
}
