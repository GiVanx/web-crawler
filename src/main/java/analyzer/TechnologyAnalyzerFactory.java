package analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import file.FileReader;
import json.JsonReader;

public class TechnologyAnalyzerFactory {

    public TechnologyAnalyzer create() {
        return TechnologyAnalyzer.getInstance(new FileReader(new JsonReader(new ObjectMapper())));
    }
}
