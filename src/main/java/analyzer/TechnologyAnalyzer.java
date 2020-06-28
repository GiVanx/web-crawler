package analyzer;

import analyzer.exceptions.TechnologyAnalyzerException;
import analyzer.model.App;
import analyzer.model.Category;
import analyzer.model.TechnologyData;
import file.IFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TechnologyAnalyzer {

    private static TechnologyAnalyzer technologyAnalyzer;
    private IFileReader fileReader;
    private static final String TECHNOLOGY_DATA_FILE_PATH = "./apps.json";
    private TechnologyData technologyData;

    public TechnologyAnalyzer(IFileReader fileReader) {
        this.fileReader = fileReader;
    }

    private void init() {
        try {
            System.out.println("[Technology Analyzer] Loading technology information");
            this.technologyData = fileReader.read(TECHNOLOGY_DATA_FILE_PATH, TechnologyData.class);
            System.out.println(technologyData);
            System.out.println("[Technology Analyzer] Loading technology information succeeded");
        } catch (IOException e) {
            System.out.println("[Technology Analyzer] Loading technology information failed");
            throw new TechnologyAnalyzerException("Failed to load technology data", e);
        }
    }

    public static TechnologyAnalyzer getInstance(IFileReader fileReader) {
        if (technologyAnalyzer == null) {
            technologyAnalyzer = new TechnologyAnalyzer(fileReader);
            technologyAnalyzer.init();
        }
        return technologyAnalyzer;
    }

    public String getTechnologyName(String srcAttribute) {

        List<String> technologyNames = new ArrayList<>();
        Map<String, App> technologyMap = this.technologyData.getApps();
        technologyMap.keySet().forEach(technologyName -> {
            App app = technologyMap.get(technologyName);
            if (app.getScriptPatterns() != null && app.getCats().contains(Category.JAVASCRIPT_LIBRARY.getValue())) {
                List<Pattern> scriptPatterns = technologyMap.get(technologyName).getScriptPatterns();
                if (scriptPatterns != null && scriptPatterns.stream().anyMatch(pattern -> pattern.matcher(srcAttribute).find())) {
                    technologyNames.add(technologyName);
                }
            }
        });
        return (technologyNames.size() > 0) ? technologyNames.get(0) : null;
    }
}
