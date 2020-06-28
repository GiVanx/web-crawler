package crawler;

import analyzer.factory.TechnologyAnalyzerFactory;
import crawler.utils.URLReader;

public class CrawlerWorkerFactory implements ICrawlerWorkerFactory {

    @Override
    public CrawlerWorker create(String url) {
        return new CrawlerWorker(url, new URLReader(), new TechnologyAnalyzerFactory());
    }
}
