package crawler.analyzer.factory;

import crawler.analyzer.ITechnologyAnalyzer;

public interface ITechnologyAnalyzerFactory {

    ITechnologyAnalyzer getOrCreate();
}
