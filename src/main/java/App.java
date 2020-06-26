import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GoogleSearchResult;

import java.io.*;
import java.net.URL;


public class App {

    public static void main(String[] args) throws IOException {

        String googleUrl = "https://www.googleapis.com/customsearch/v1?key=AIzaSyD8cAOUT0Yy1pSmUgcauBbRm-1iLyTvHZI&cx=009401593635955574171:7nrrgvnyj_k&q=cars";
        String charSet = "UTF-8";

        URL url = new URL(googleUrl);

        BufferedReader reader = new BufferedReader(new FileReader("mockGoogleResult.txt"));

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GoogleSearchResult searchResult = objectMapper.readValue(new File("mockGoogleResult.txt"), GoogleSearchResult.class);

        System.out.println("search result: " + searchResult);
    }
}
