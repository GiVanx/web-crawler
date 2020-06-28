package google;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.utils.AppProperties;
import google.model.GoogleSearchResult;
import google.model.exception.GoogleSearchException;
import http.IHttpService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GoogleSearcher implements IGoogleSearcher {

    private String searchEngineID;
    private String searchApiKey;
    private IHttpService httpService;
    private static final String GOOGLE_SEARCH_URL_FORMAT = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s";

    public GoogleSearcher(IHttpService httpService) {
        this.httpService = httpService;
        this.searchEngineID = AppProperties.getInstance().getProperties().getProperty("google.search.engine.id");
        this.searchApiKey = AppProperties.getInstance().getProperties().getProperty("google.search.api.key");
    }

    @Override
    public GoogleSearchResult search(String searchTerm) {

        try {
//            GoogleSearchResult searchResult = this.httpService.get(getSearchUrl(searchTerm), GoogleSearchResult.class);
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            GoogleSearchResult searchResult = objectMapper.readValue(new File("mockGoogleResult.txt"), GoogleSearchResult.class);

            return searchResult;
        } catch (Exception e) {
            throw new GoogleSearchException(e);
        }
    }

    String getSearchUrl(String searchTerm) {
        return String.format(GOOGLE_SEARCH_URL_FORMAT,
                this.searchApiKey,
                this.searchEngineID,
                searchTerm
        );
    }
}
