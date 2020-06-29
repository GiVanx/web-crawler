package crawler.factory;

import crawler.analyzer.factory.ITechnologyAnalyzerFactory;
import crawler.CrawlerWorker;
import crawler.utils.http.URLReader;

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
