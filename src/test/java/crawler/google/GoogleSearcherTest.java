package crawler.google;

import crawler.google.model.GoogleSearchResult;
import crawler.google.model.exception.GoogleSearchException;
import crawler.utils.stream.IJsonStreamReader;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleSearcherTest extends TestCase {

    @Mock
    private IJsonStreamReader streamReader;

    @Mock
    private GoogleSearchResult searchResult;

    @Test
    public void testSearchWhenNoError() throws IOException {
        GoogleSearcher googleSearcher = new GoogleSearcher(streamReader);

        when(streamReader.read(any(), eq(GoogleSearchResult.class)))
                .thenReturn(searchResult);

        GoogleSearchResult actualResult = googleSearcher.search("some");
        assertEquals(searchResult, actualResult);
    }

    @Test(expected = GoogleSearchException.class)
    public void testSearchWhenHttpErrorOccurs() throws IOException {
        GoogleSearcher googleSearcher = new GoogleSearcher(streamReader);

        when(streamReader.read(any(), eq(GoogleSearchResult.class)))
                .thenThrow(mock(IOException.class));

        googleSearcher.search("some");
    }
}