import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static AppProperties appProperties;
    private Properties properties;
    private static final String APP_PROPERTIES_FILE_PATH = "src/main/resources/app.properties";

    private AppProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public static AppProperties getInstance() throws IOException {
        if (appProperties == null) {
            InputStream input = new FileInputStream(APP_PROPERTIES_FILE_PATH);

            Properties properties = new Properties();
            properties.load(input);

            appProperties = new AppProperties(properties);
        }
        return appProperties;
    }
}
