package analyzer.factory;

import analyzer.TechnologyAnalyzer;

public interface ITechnologyAnalyzerFactory {

    TechnologyAnalyzer getOrCreate();
}
