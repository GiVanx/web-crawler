package crawler.analyzer.model;

import java.util.Collections;
import java.util.Map;

public class TechnologyData {

    private Map<String, App> apps;

    public Map<String, App> getApps() {
        return Collections.unmodifiableMap(apps);
    }

    public void filterJsLibraries() {
        this.apps.entrySet().removeIf(entry -> {
            App app = entry.getValue();

            boolean categoryCondition = app.getCats() != null &&
                    (app.getCats().contains(Category.BLOGS.getValue())
                    || app.getCats().contains(Category.CMS.getValue()));

            return app.getScriptPatterns() == null
                    || categoryCondition;
        });
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
