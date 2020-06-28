package crawler;

public interface ICrawlerWorkerFactory {

    CrawlerWorker create(String url);
}
