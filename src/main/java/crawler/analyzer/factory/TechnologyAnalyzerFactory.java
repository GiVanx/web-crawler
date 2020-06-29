package crawler.analyzer.factory;

import crawler.analyzer.TechnologyAnalyzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.utils.file.FileReader;
import crawler.utils.json.JsonReader;

public class TechnologyAnalyzerFactory implements ITechnologyAnalyzerFactory {

    public TechnologyAnalyzer getOrCreate() {
        return TechnologyAnalyzer.getInstance(new FileReader(new JsonReader(new ObjectMapper())));
    }
}
