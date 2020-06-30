package crawler.factory;

import crawler.ICrawlerWorker;

public interface ICrawlerWorkerFactory {

    ICrawlerWorker create(String url);
}
