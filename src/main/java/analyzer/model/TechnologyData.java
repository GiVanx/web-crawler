package analyzer.model;

import java.util.Map;

public class TechnologyData {

    Map<String, App> apps;

    public Map<String, App> getApps() {
        return apps;
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
