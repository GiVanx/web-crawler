package crawler.google;

import crawler.utils.AppProperties;
import crawler.google.model.GoogleSearchResult;
import crawler.google.model.exception.GoogleSearchException;
import crawler.utils.stream.IJsonStreamReader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleSearcher implements IGoogleSearcher {

    private final String searchEngineID;
    private final String searchApiKey;
    private final IJsonStreamReader streamReader;
    private static final String GOOGLE_SEARCH_URL_FORMAT = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s";

    public GoogleSearcher(IJsonStreamReader streamReader) {
        this.streamReader = streamReader;
        this.searchEngineID = AppProperties.getInstance().getProperties().getProperty("google.search.engine.id");
        this.searchApiKey = AppProperties.getInstance().getProperties().getProperty("google.search.api.key");
    }

    @Override
    public GoogleSearchResult search(String searchTerm) {

        try {
            System.out.println("[Google Search] Searching for '" + searchTerm + "'...");

            GoogleSearchResult searchResult = this.streamReader.read(getSearchUrl(searchTerm), GoogleSearchResult.class);

            System.out.println("[Google Search][Successful]");

            return searchResult;
        } catch (Exception e) {
            System.out.println("[Google Search][Failed]");
            throw new GoogleSearchException(e);
        }
    }

    private String getSearchUrl(String searchTerm) throws UnsupportedEncodingException {
        return String.format(GOOGLE_SEARCH_URL_FORMAT,
                this.searchApiKey,
                this.searchEngineID,
                URLEncoder.encode(searchTerm, StandardCharsets.UTF_8.toString()));
    }
}
