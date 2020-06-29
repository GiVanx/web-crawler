package crawler.analyzer.model;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TechnologyDataTest {

    @Test
    public void testFilterJsLibrariesForAppsWithNullScriptPatternsAndNoCategories() {
        TechnologyData technologyData = new TechnologyData();

        Map<String, App> apps = new HashMap<>();
        apps.put("tech1", mockApp(null));
        apps.put("tech2", mockApp("pattern2"));

        technologyData.setApps(apps);
        technologyData.filterJsLibraries();

        assertTrue(technologyData.getApps().size() == 1);
        assertTrue(technologyData.getApps().containsKey("tech2"));
        assertTrue(technologyData.getApps().get("tech2").getScriptPatterns().get(0).toString().equals("pattern2"));
    }

    @Test
    public void testFilterJsLibrariesForAppsWithNotNullScriptPatternsAndNoBlogsOrCms() {
        TechnologyData technologyData = new TechnologyData();

        Map<String, App> apps = new HashMap<>();
        apps.put("tech1", mockApp("pattern1"));
        apps.put("tech2", mockApp("pattern2"));

        technologyData.setApps(apps);
        technologyData.filterJsLibraries();

        assertTrue(technologyData.getApps().size() == 2);
        assertTrue(technologyData.getApps().containsKey("tech1"));
        assertTrue(technologyData.getApps().containsKey("tech2"));
        assertTrue(technologyData.getApps().get("tech1").getScriptPatterns().get(0).toString().equals("pattern1"));
        assertTrue(technologyData.getApps().get("tech2").getScriptPatterns().get(0).toString().equals("pattern2"));
    }

    @Test
    public void testFilterJsLibrariesForAppsWithNotNullScriptPatternsAndWithBlogs() {
        TechnologyData technologyData = new TechnologyData();

        Map<String, App> apps = new HashMap<>();
        App blogApp = mockApp("pattern1");
        when(blogApp.getCats()).thenReturn(Arrays.asList(Category.BLOGS.getValue()));

        apps.put("tech1", blogApp);
        apps.put("tech2", new App("pattern2"));

        technologyData.setApps(apps);
        technologyData.filterJsLibraries();

        assertTrue(technologyData.getApps().size() == 1);
        assertTrue(technologyData.getApps().containsKey("tech2"));
        assertTrue(technologyData.getApps().get("tech2").getScriptPatterns().get(0).toString().equals("pattern2"));
    }

    @Test
    public void testFilterJsLibrariesForAppsWithNotNullScriptPatternsAndWithCms() {
        TechnologyData technologyData = new TechnologyData();

        Map<String, App> apps = new HashMap<>();
        App cmsApp = mockApp("pattern1");
        when(cmsApp.getCats()).thenReturn(Arrays.asList(Category.CMS.getValue()));

        apps.put("tech1", cmsApp);
        apps.put("tech2", new App("pattern2"));

        technologyData.setApps(apps);
        technologyData.filterJsLibraries();

        assertTrue(technologyData.getApps().size() == 1);
        assertTrue(technologyData.getApps().containsKey("tech2"));
        assertTrue(technologyData.getApps().get("tech2").getScriptPatterns().get(0).toString().equals("pattern2"));
    }

    private App mockApp(String... patterns) {
        App app = mock(App.class);
        if (patterns != null) {
            List<String> patternList = Arrays.asList(patterns);
            when(app.getScriptPatterns()).thenReturn(patternList.stream().map(p -> Pattern.compile(p)).collect(Collectors.toList()));
        } else {
            when(app.getScriptPatterns()).thenReturn(null);
        }
        return app;
    }
}