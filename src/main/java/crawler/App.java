package crawler;

import analyzer.TechnologyAnalyzer;
import analyzer.factory.TechnologyAnalyzerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class App {

    public static String getLibraryName(String scriptSrcAttr) {
        String res = scriptSrcAttr.substring(scriptSrcAttr.lastIndexOf('/') + 1);

        int dotIndex = res.indexOf('.');
        if (dotIndex >= 0) {
            res = res.substring(0, res.indexOf('.'));
        }

        return res;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

//        WebCrawler crawler = new WebCrawler(new GoogleSearcher(new HttpService(new ObjectMapper())), new CrawlerWorkerFactory());
//
//        System.out.println("Final: " + crawler.crawl("cars"));
        TechnologyAnalyzer technologyAnalyzer = new TechnologyAnalyzerFactory().getOrCreate();
        technologyAnalyzer.getTechnologyName("https://www.baeldung.com/wp-includes/js/jquery/jquery.js?ver=1.12.4-wp");

//        System.out.println("https://www.baeldung.com/wp-includes/js/jquery/jquery.js?ver=1.12.4-wp".matches("jquery.*\\.js(?:\\?ver(?:sion)?=([\\d.]+))?\\;version:\\1"));
//
//        Pattern p = Pattern.compile("jquery.*\\.js(?:\\?ver(?:sion)?=([\\d.]+))?\\;version:\\1");
//        Matcher matcher = p.matcher("jquery-3.0.0.js");
//
//        while (matcher.find()) {
//            System.out.println(matcher.start() + " " + matcher.end());
//        }
    }
}
