package crawler.google.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResult {

    private List<GoogleSearchResultItem> items;

    public List<GoogleSearchResultItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<GoogleSearchResultItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "GoogleSearchResult{" +
                "items=" + items.stream().map(item -> items.toString()).collect(Collectors.toList()) +
                "}";
    }
}
