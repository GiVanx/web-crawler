package crawler.analyzer;

import crawler.analyzer.exceptions.TechnologyAnalyzerException;
import crawler.analyzer.model.App;
import crawler.analyzer.model.TechnologyData;
import crawler.utils.stream.IJsonStreamReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TechnologyAnalyzerTest {

    @Before
    public void resetSingletonState() throws NoSuchFieldException, IllegalAccessException {
        Field instance = TechnologyAnalyzer.class.getDeclaredField("technologyAnalyzer");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void testGetJsLibraryNameWhenNoTechnologyDataExist() throws IOException {
        IJsonStreamReader fileReader = mock(IJsonStreamReader.class);
        TechnologyData technologyData = mock(TechnologyData.class);
        when(fileReader.read(TechnologyAnalyzer.TECHNOLOGY_DATA_FILE_PATH, TechnologyData.class))
                .thenReturn(technologyData);

        when(technologyData.getApps()).thenReturn(new HashMap<>());

        String result = TechnologyAnalyzer.getInstance(fileReader).getJsLibraryName("");
        assertEquals(null, result);
    }

    @Test
    public void testGetJsLibraryNameWhenMatchingTechnologyFound() throws IOException {
        IJsonStreamReader fileReader = mock(IJsonStreamReader.class);
        TechnologyData technologyData = mock(TechnologyData.class);
        when(fileReader.read(TechnologyAnalyzer.TECHNOLOGY_DATA_FILE_PATH, TechnologyData.class))
                .thenReturn(technologyData);

        Map<String, App> technologyMap = new HashMap<>();
        App app = mock(App.class);
        when(app.getScriptPatterns()).thenReturn(Arrays.asList(Pattern.compile(".*")));
        technologyMap.put("tech1", app);

        when(technologyData.getApps()).thenReturn(technologyMap);

        String result = TechnologyAnalyzer.getInstance(fileReader).getJsLibraryName("some lib");
        assertEquals("tech1", result);
    }

    @Test
    public void testGetJsLibraryNameWhenNoMatchingTechnologyFound() throws IOException {
        IJsonStreamReader fileReader = mock(IJsonStreamReader.class);
        TechnologyData technologyData = mock(TechnologyData.class);
        when(fileReader.read(TechnologyAnalyzer.TECHNOLOGY_DATA_FILE_PATH, TechnologyData.class))
                .thenReturn(technologyData);

        Map<String, App> technologyMap = new HashMap<>();
        App app = mock(App.class);
        when(app.getScriptPatterns()).thenReturn(Arrays.asList(Pattern.compile("1234")));
        technologyMap.put("tech1", app);

        when(technologyData.getApps()).thenReturn(technologyMap);

        String result = TechnologyAnalyzer.getInstance(fileReader).getJsLibraryName("some lib");
        assertEquals(null, result);
    }

    @Test(expected = TechnologyAnalyzerException.class)
    public void testGetInstanceWhenFailedToReadTechnologyData() throws IOException {
        IJsonStreamReader fileReader = mock(IJsonStreamReader.class);
        when(fileReader.read(TechnologyAnalyzer.TECHNOLOGY_DATA_FILE_PATH, TechnologyData.class))
                .thenThrow(new TechnologyAnalyzerException("Failed to load technology data", new IOException()));

        TechnologyAnalyzer.getInstance(fileReader);
    }
}