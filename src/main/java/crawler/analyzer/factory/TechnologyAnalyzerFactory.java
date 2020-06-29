package crawler.analyzer.factory;

import crawler.analyzer.TechnologyAnalyzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.utils.file.JsonStreamReader;
import crawler.utils.json.JsonReader;
import crawler.utils.stream.FileStream;

public class TechnologyAnalyzerFactory implements ITechnologyAnalyzerFactory {

    public TechnologyAnalyzer getOrCreate() {
        return TechnologyAnalyzer.getInstance(new JsonStreamReader(new JsonReader(new ObjectMapper()),
                new FileStream()));
    }
}
