package crawler;

import java.util.Map;
import java.util.concurrent.Callable;

public interface ICrawlerWorker extends Callable<Map<String, Integer>> {
}
