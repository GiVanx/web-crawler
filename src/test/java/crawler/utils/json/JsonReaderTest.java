package crawler.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonReaderTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JsonReader jsonReader;

    static class TestClass {
    }

    @Test
    public void testRead() throws IOException {
        String dummyInput = "Some input stream";

        InputStream inputStream = new ByteArrayInputStream(dummyInput.getBytes());

        TestClass testClass = mock(TestClass.class);
        when(objectMapper.readValue(dummyInput, TestClass.class))
                .thenReturn(testClass);

        TestClass actualResult = jsonReader.read(inputStream, TestClass.class);
        assertEquals(testClass, actualResult);
    }
}