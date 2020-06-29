package crawler.utils.file;

import crawler.google.model.GoogleSearchResult;
import crawler.utils.json.IJsonReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.TestUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileReaderTest {

    @Mock
    private IJsonReader jsonReader;

    private String resourceName = "dummy.txt";

    private String resourceAbsolutePath;

    private TestUtils testUtils = new TestUtils();

    @Before
    public void init() {
        resourceAbsolutePath = testUtils.getResource(resourceName).getPath();
    }

    @Test
    public void testRead() throws IOException {
        FileReader fileReader = new FileReader(jsonReader);
        GoogleSearchResult searchResult = mock(GoogleSearchResult.class);
        when(jsonReader.read(any(), eq(GoogleSearchResult.class))).thenReturn(
            searchResult
        );

        GoogleSearchResult actualResult = fileReader.read(resourceAbsolutePath, GoogleSearchResult.class);

        assertEquals(searchResult, actualResult);
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadWhenFileNotFoundException() throws IOException {
        FileReader fileReader = new FileReader(jsonReader);

        fileReader.read("invalid/path", GoogleSearchResult.class);
    }
}