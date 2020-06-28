package crawler.factory;

import crawler.CrawlerWorker;

public interface ICrawlerWorkerFactory {

    CrawlerWorker create(String url);
}
