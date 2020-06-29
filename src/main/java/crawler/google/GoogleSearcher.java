package crawler.google;

import crawler.utils.AppProperties;
import crawler.google.model.GoogleSearchResult;
import crawler.google.model.exception.GoogleSearchException;
import crawler.utils.http.IHttpService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleSearcher implements IGoogleSearcher {

    private String searchEngineID;
    private String searchApiKey;
    private IHttpService httpService;
    private static final String GOOGLE_SEARCH_URL_FORMAT = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s";

    public GoogleSearcher(IHttpService httpService) {
        this.httpService = httpService;
        this.searchEngineID = AppProperties.getInstance().getProperties().getProperty("google.search.engine.id");
        this.searchApiKey = AppProperties.getInstance().getProperties().getProperty("google.search.api.key");

        System.out.println(this.searchEngineID);
        System.out.println(this.searchApiKey);
    }

    @Override
    public GoogleSearchResult search(String searchTerm) {

        try {
            System.out.println("[Google Search] Searching for '" + searchTerm + "'...");
            GoogleSearchResult searchResult = this.httpService.get(getSearchUrl(searchTerm), GoogleSearchResult.class);
            System.out.println("[Google Search][Successful]");
//            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            GoogleSearchResult searchResult = objectMapper.readValue(new File("mockGoogleResult.txt"), GoogleSearchResult.class);

            return searchResult;
        } catch (Exception e) {
            System.out.println("[Google Search][Failed]");
            throw new GoogleSearchException(e);
        }
    }

    String getSearchUrl(String searchTerm) throws UnsupportedEncodingException {
        return String.format(GOOGLE_SEARCH_URL_FORMAT,
                this.searchApiKey,
                this.searchEngineID,
                URLEncoder.encode(searchTerm, StandardCharsets.UTF_8.toString()));
    }
}
