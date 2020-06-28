package crawler;

import analyzer.factory.ITechnologyAnalyzerFactory;
import analyzer.factory.TechnologyAnalyzerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.factory.CrawlerWorkerFactory;
import google.GoogleSearcher;
import http.HttpService;
import json.JsonReader;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ITechnologyAnalyzerFactory technologyAnalyzerFactory = new TechnologyAnalyzerFactory();

        WebCrawler crawler = new WebCrawler(new GoogleSearcher(new HttpService(new JsonReader(new ObjectMapper()))), new CrawlerWorkerFactory(technologyAnalyzerFactory));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n[Press 'e' to exit] >> ");

            String searchTerm = scanner.nextLine();

            if (searchTerm.length() == 1 && searchTerm.charAt(0) == 'e') {
                break;
            }

            System.out.println();
            List<String> topJsLibraries = crawler.crawl(searchTerm);

            if (topJsLibraries.size() > 0) {
                System.out.println("\nTop 5 Javascript libraries:");
                for (int i = 0; i < topJsLibraries.size(); i++) {
                    System.out.println(String.format("%d) %s", i + 1, topJsLibraries.get(i)));
                }
            }
        }

        crawler.stop();
    }
}
