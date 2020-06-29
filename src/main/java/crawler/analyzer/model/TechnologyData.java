package crawler.analyzer.model;

import java.util.Collections;
import java.util.Map;

public class TechnologyData {

    Map<String, App> apps;

    public Map<String, App> getApps() {
        return Collections.unmodifiableMap(apps);
    }

    public void filterJsLibraries() {
        this.apps.entrySet().removeIf(entry -> {
            App app = entry.getValue();
            return app.getScriptPatterns() == null
                    || (app.getCats().contains(Category.BLOGS.getValue()))
                    || (app.getCats().contains(Category.CMS.getValue()));
        });
    }

    public void removeApp(String key) {
        apps.remove(key);
    }

    public void setApps(Map<String, App> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "TechnologyData{" +
                "apps=" + apps +
                '}';
    }
}
