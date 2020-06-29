package crawler.analyzer;

import crawler.analyzer.exceptions.TechnologyAnalyzerException;
import crawler.analyzer.model.App;
import crawler.analyzer.model.TechnologyData;
import crawler.utils.file.IStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TechnologyAnalyzer implements ITechnologyAnalyzer {

    private static TechnologyAnalyzer technologyAnalyzer;
    private IStreamReader fileReader;
    public static final String TECHNOLOGY_DATA_FILE_PATH = "./apps.json";
    private TechnologyData technologyData;

    private TechnologyAnalyzer(IStreamReader fileReader) {
        this.fileReader = fileReader;
    }

    private void init() {
        try {
            System.out.println("[Technology Analyzer] Loading technology information");
            technologyData = fileReader.read(TECHNOLOGY_DATA_FILE_PATH, TechnologyData.class);

            technologyData.filterJsLibraries();

            System.out.println(technologyData);
            System.out.println("[Technology Analyzer] Loading technology information succeeded");
        } catch (IOException e) {
            System.out.println("[Technology Analyzer] Loading technology information failed");
            throw new TechnologyAnalyzerException("Failed to load technology data", e);
        }
    }

    public static TechnologyAnalyzer getInstance(IStreamReader fileReader) {
        if (technologyAnalyzer == null) {
            technologyAnalyzer = new TechnologyAnalyzer(fileReader);
            technologyAnalyzer.init();
        }
        return technologyAnalyzer;
    }

    public String getJsLibraryName(String srcAttribute) {

        List<String> technologyNames = new ArrayList<>();
        Map<String, App> technologyMap = this.technologyData.getApps();
        technologyMap.keySet().forEach(technologyName -> {
            App app = technologyMap.get(technologyName);
            if (app.getScriptPatterns() != null) {
                List<Pattern> scriptPatterns = technologyMap.get(technologyName).getScriptPatterns();
                if (scriptPatterns != null && scriptPatterns.stream().anyMatch(pattern -> pattern.matcher(srcAttribute).find())) {
                    technologyNames.add(technologyName);
                }
            }
        });
        return (technologyNames.size() > 0) ? technologyNames.get(0) : null;
    }
}
