package crawler;

import crawler.analyzer.ITechnologyAnalyzer;
import crawler.utils.http.IHTMLReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerWorkerTest {

    @InjectMocks
    private CrawlerWorker crawlerWorker;

    @Mock
    private IHTMLReader htmlReader;

    @Mock
    private ITechnologyAnalyzer technologyAnalyzer;

    @Test
    public void testCall() {

        when(htmlReader.getAttributes(CrawlerWorker.SCRIPT_TAG_NAME, CrawlerWorker.SCRIPT_TAG_SRC_ATTRIBUTE_NAME))
                .thenReturn(Arrays.asList("attr1", "attr2", "attr2"));

        when(technologyAnalyzer.getJsLibraryName("attr1")).thenReturn("lib1");
        when(technologyAnalyzer.getJsLibraryName("attr2")).thenReturn("lib2");

        Map<String, Integer> actualResult = crawlerWorker.call();
        assertEquals(2, actualResult.size());
        assertTrue(actualResult.containsKey("lib1"));
        assertTrue(actualResult.containsKey("lib2"));
        assertEquals((Integer) 1, actualResult.get("lib1"));
        assertEquals((Integer) 2, actualResult.get("lib2"));
    }

    @Test
    public void testCallWithNoAttributes() {

        when(htmlReader.getAttributes(CrawlerWorker.SCRIPT_TAG_NAME, CrawlerWorker.SCRIPT_TAG_SRC_ATTRIBUTE_NAME))
                .thenReturn(Arrays.asList());

        Map<String, Integer> actualResult = crawlerWorker.call();
        assertEquals(0, actualResult.size());
    }

    @Test
    public void testCallWhenException() throws IOException {
        String url = "some/url";
        CrawlerWorker crawlerWorker = new CrawlerWorker(url, htmlReader, technologyAnalyzer);
        doThrow(new IOException()).when(this.htmlReader).init(url);
        Map<String, Integer> actualResult = crawlerWorker.call();
        assertEquals(0, actualResult.size());
    }
}