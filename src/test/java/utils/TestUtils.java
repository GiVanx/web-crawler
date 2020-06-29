package utils;

import java.io.File;

public class TestUtils {

    public File getResource(String resourceName) {
        return new File("src/test/resources/" + resourceName);
    }
}
