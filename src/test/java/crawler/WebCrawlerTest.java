package crawler;

import crawler.factory.ICrawlerWorkerFactory;
import crawler.google.IGoogleSearcher;
import crawler.google.model.GoogleSearchResult;
import crawler.google.model.GoogleSearchResultItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerTest {

    @InjectMocks
    private WebCrawler webCrawler;

    @Mock
    private ICrawlerWorkerFactory workerFactory;

    @Mock
    private IGoogleSearcher googleSearcher;

    @Mock
    private GoogleSearchResult searchResult;

    @Test
    public void testCrawl() {

        String searchTerm = "some term";

        when(googleSearcher.search(searchTerm)).thenReturn(searchResult);

        List<GoogleSearchResultItem> mockItems = Arrays.asList(getMockItem("url/link1"),
                getMockItem("url/link2"));
        when(searchResult.getItems()).thenReturn(mockItems);

        CrawlerWorker link1Worker = mockWorker(getMap(Arrays.asList("lib1", "lib2", "lib3", "lib4", "lib5"), Arrays.asList(1, 1, 2, 3, 2)));
        when(workerFactory.create("url/link1")).thenReturn(link1Worker);

        CrawlerWorker link2Worker = mockWorker(getMap(Arrays.asList("lib2", "lib3"), Arrays.asList(2, 2)));
        when(workerFactory.create("url/link2")).thenReturn(link2Worker);

        List<String> result = webCrawler.crawl(searchTerm);

        System.out.println(result);

        assertEquals(5, result.size());
        assertEquals("lib3", result.get(0));
        assertEquals("lib2", result.get(1));
        assertEquals("lib4", result.get(2));
        assertEquals("lib5", result.get(3));
        assertEquals("lib1", result.get(4));
    }

    private Map<String, Integer> getMap(List<String> libs, List<Integer> libCounts) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < libs.size(); ++i) {
            map.put(libs.get(i), libCounts.get(i));
        }
        return map;
    }

    private CrawlerWorker mockWorker(Map<String, Integer> jsLibNameToCountMap) {

        CrawlerWorker worker = mock(CrawlerWorker.class);
        when(worker.call()).thenReturn(jsLibNameToCountMap);

        return worker;
    }

    private GoogleSearchResultItem getMockItem(String link) {
        GoogleSearchResultItem item = mock(GoogleSearchResultItem.class);
        when(item.getLink()).thenReturn(link);
        return item;
    }

}