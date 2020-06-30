package crawler.factory;

import crawler.ICrawlerWorker;
import crawler.analyzer.factory.ITechnologyAnalyzerFactory;
import crawler.CrawlerWorker;
import crawler.utils.html.HTMLReader;

public class CrawlerWorkerFactory implements ICrawlerWorkerFactory {

    private ITechnologyAnalyzerFactory technologyAnalyzerFactory;

    public CrawlerWorkerFactory(ITechnologyAnalyzerFactory technologyAnalyzerFactory) {
        this.technologyAnalyzerFactory = technologyAnalyzerFactory;
    }

    @Override
    public ICrawlerWorker create(String url) {
        return new CrawlerWorker(url, new HTMLReader(), technologyAnalyzerFactory.getOrCreate());
    }
}
