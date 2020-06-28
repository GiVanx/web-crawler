package crawler.factory;

import analyzer.factory.ITechnologyAnalyzerFactory;
import crawler.CrawlerWorker;
import http.URLReader;

public class CrawlerWorkerFactory implements ICrawlerWorkerFactory {

    private ITechnologyAnalyzerFactory technologyAnalyzerFactory;

    public CrawlerWorkerFactory(ITechnologyAnalyzerFactory technologyAnalyzerFactory) {
        this.technologyAnalyzerFactory = technologyAnalyzerFactory;
    }

    @Override
    public CrawlerWorker create(String url) {
        return new CrawlerWorker(url, new URLReader(), technologyAnalyzerFactory.getOrCreate());
    }
}
