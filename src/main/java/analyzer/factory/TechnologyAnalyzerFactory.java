package analyzer.factory;

import analyzer.TechnologyAnalyzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import file.FileReader;
import json.JsonReader;

public class TechnologyAnalyzerFactory implements ITechnologyAnalyzerFactory {

    public TechnologyAnalyzer getOrCreate() {
        return TechnologyAnalyzer.getInstance(new FileReader(new JsonReader(new ObjectMapper())));
    }
}
