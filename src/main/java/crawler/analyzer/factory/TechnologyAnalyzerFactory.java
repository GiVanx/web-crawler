package crawler.analyzer.factory;

import crawler.analyzer.ITechnologyAnalyzer;
import crawler.analyzer.TechnologyAnalyzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.utils.stream.JsonStreamReader;
import crawler.utils.json.JsonReader;
import crawler.utils.stream.FileStreamMaker;
import crawler.utils.stream.IOInputStreamService;

public class TechnologyAnalyzerFactory implements ITechnologyAnalyzerFactory {

    public ITechnologyAnalyzer getOrCreate() {
        return TechnologyAnalyzer.getInstance(new JsonStreamReader(new JsonReader(new ObjectMapper()),
                new IOInputStreamService(new FileStreamMaker())));
    }
}
