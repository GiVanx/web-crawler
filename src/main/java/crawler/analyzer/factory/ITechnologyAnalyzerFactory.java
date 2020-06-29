package crawler.analyzer.factory;

import crawler.analyzer.TechnologyAnalyzer;

public interface ITechnologyAnalyzerFactory {

    TechnologyAnalyzer getOrCreate();
}
