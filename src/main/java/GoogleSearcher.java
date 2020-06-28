import http.IHttpService;
import model.GoogleSearchResult;
import model.exceptions.CrawlerException;

public class GoogleSearcher {

    private String searchEngineID;
    private String searchApiKey;
    private IHttpService httpService;

    public GoogleSearcher(IHttpService httpService) {
        this.httpService = httpService;
        this.searchEngineID = AppProperties.getInstance().getProperties().getProperty("google.search.engine.id");
        this.searchApiKey = AppProperties.getInstance().getProperties().getProperty("google.search.api.key");
    }

    GoogleSearchResult search(String searchTerm) {

        try {
            GoogleSearchResult searchResult = this.httpService.get(getSearchUrl(searchTerm), GoogleSearchResult.class);
            return searchResult;
        } catch (Exception e) {
            throw new CrawlerException(e);
        }
    }

    String getSearchUrl(String searchTerm) {
        return String.format("https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s",
                this.searchApiKey,
                this.searchEngineID,
                searchTerm
        );
    }
}
