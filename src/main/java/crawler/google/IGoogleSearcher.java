package crawler.google;

import crawler.google.model.GoogleSearchResult;

public interface IGoogleSearcher {

    GoogleSearchResult search(String searchTerm);
}
