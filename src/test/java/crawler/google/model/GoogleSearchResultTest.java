package crawler.google.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GoogleSearchResultTest {

    @Test
    public void testSetGetItems() {
        GoogleSearchResultItem item1 = mock(GoogleSearchResultItem.class);
        GoogleSearchResultItem item2 = mock(GoogleSearchResultItem.class);
        GoogleSearchResultItem item3 = mock(GoogleSearchResultItem.class);

        GoogleSearchResult result = new GoogleSearchResult();
        result.setItems(Arrays.asList(item1, item2, item3));

        List<GoogleSearchResultItem> items = result.getItems();
        assertEquals(3, items.size());
        assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));
        assertEquals(item3, items.get(2));
    }
}