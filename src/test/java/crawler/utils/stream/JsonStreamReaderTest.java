package crawler.utils.stream;

import crawler.utils.json.IJsonReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonStreamReaderTest {

    @Mock
    private IJsonReader jsonReader;

    @Mock
    private IfaceIOInputStreamService stream;

    @Mock
    private InputStream inputStream;

    @InjectMocks
    private JsonStreamReader jsonStreamReader;

    static class TestClass {
    }

    @Test
    public void testRead() throws IOException {

        String path = "path/to/resource";

        TestClass searchResult = mock(TestClass.class);
        when(stream.open(path)).thenReturn(inputStream);
        when(jsonReader.read(inputStream, TestClass.class)).thenReturn(
            searchResult
        );
        TestClass actualResult = jsonStreamReader.read(path, TestClass.class);

        assertEquals(searchResult, actualResult);
    }

    @Test(expected = IOException.class)
    public void testReadWhenFileNotFoundException() throws IOException {
        String path = "invalid/path";

        when(stream.open(path)).thenThrow(IOException.class);
        jsonStreamReader.read(path, TestClass.class);
    }
}