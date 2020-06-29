package crawler.google.model;

public class GoogleSearchResultItem {

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "{" +
                "link='" + link + '\'' +
                '}';
    }
}
