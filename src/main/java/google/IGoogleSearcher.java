package google;

import google.model.GoogleSearchResult;

public interface IGoogleSearcher {

    GoogleSearchResult search(String searchTerm);
}
