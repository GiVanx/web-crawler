package crawler.utils.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IOInputStreamServiceTest {

    @InjectMocks
    private IOInputStreamService inputStreamService;

    @Mock
    private InputStream inputStream;

    @Mock
    private IOInputStreamMaker inputStreamMaker;

    @Test
    public void testOpen() throws IOException {
        String url = "some/url";
        when(inputStreamMaker.getStream(url)).thenReturn(inputStream);

        InputStream actualResult = inputStreamService.open(url);
        assertEquals(inputStream, actualResult);
    }

    @Test(expected = IOException.class)
    public void testOpenWithException() throws IOException {
        String url = "some/url";
        when(inputStreamMaker.getStream(url)).thenThrow(new IOException());

        inputStreamService.open(url);
    }
}