package crawler.google.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoogleSearchResultItemTest {

    @Test
    public void testSetGetLink() {
        String link = "some/link";

        GoogleSearchResultItem item = new GoogleSearchResultItem();
        item.setLink(link);

        assertEquals(link, item.getLink());
    }
}