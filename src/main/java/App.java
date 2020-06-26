import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GoogleSearchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


public class App {

    public static void main(String[] args) throws IOException {

        String googleUrl = "https://www.googleapis.com/customsearch/v1?key=AIzaSyD8cAOUT0Yy1pSmUgcauBbRm-1iLyTvHZI&cx=009401593635955574171:7nrrgvnyj_k&q=cars";
        String charSet = "UTF-8";

        BufferedReader reader = new BufferedReader(new FileReader("mockGoogleResult.txt"));

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GoogleSearchResult searchResult = objectMapper.readValue(new File("mockGoogleResult.txt"), GoogleSearchResult.class);

        Document doc = Jsoup.connect(searchResult.getItems().get(1).getLink()).get();

        List<String> srcAttributes = doc.select("script").stream().map(scriptTag -> scriptTag.attr("src")).
                filter(srcAttribute -> !srcAttribute.isEmpty()).collect(Collectors.toList());

        System.out.println(srcAttributes);

        reader.close();
    }
}
