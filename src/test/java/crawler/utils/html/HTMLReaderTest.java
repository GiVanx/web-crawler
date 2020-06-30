package crawler.utils.html;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class})
public class HTMLReaderTest {

    @Mock
    private Connection connection;

    @Mock
    private Document document;

    private String url = "some/url";

    @Before
    public void init() throws IOException {
        mockStatic(Jsoup.class);

        PowerMockito.when(Jsoup.connect(url)).thenReturn(connection);
        when(connection.get()).thenReturn(document);
    }

    @Test
    public void testGetAttributesMultipleAttributes() throws IOException {

        String htmlTag = "script";
        String attributeName = "src";

        Elements elements = new Elements();
        elements.add(getElement(attributeName, "el1", "val1"));
        elements.add(getElement(attributeName, "el2", "val2"));

        when(document.select(htmlTag)).thenReturn(elements);

        HTMLReader htmlReader = new HTMLReader();
        htmlReader.init(url);

        List<String> actualResult = htmlReader.getAttributes(htmlTag, attributeName);
        assertTrue(actualResult.size() == 2);
        assertTrue(actualResult.contains("val1"));
        assertTrue(actualResult.contains("val2"));
    }

    @Test
    public void testGetAttributesNoAttributes() throws IOException {

        String htmlTag = "script";
        String attributeName = "src";

        Elements elements = new Elements();

        when(document.select(htmlTag)).thenReturn(elements);

        HTMLReader htmlReader = new HTMLReader();
        htmlReader.init(url);

        List<String> actualResult = htmlReader.getAttributes(htmlTag, attributeName);
        assertTrue(actualResult.size() == 0);
    }

    private Element getElement(String attributeName, String tag, String attributeValue) {
        Element el1 = new Element(tag);
        el1.attr(attributeName, attributeValue);
        return el1;
    }
}