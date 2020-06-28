import com.fasterxml.jackson.databind.ObjectMapper;
import google.GoogleSearcher;
import http.HttpService;
import google.model.GoogleSearchResult;

import java.io.*;


public class App {

    public static String getLibraryName(String scriptSrcAttr) {
        String res = scriptSrcAttr.substring(scriptSrcAttr.lastIndexOf('/') + 1);

        int dotIndex = res.indexOf('.');
        if (dotIndex >= 0) {
            res = res.substring(0, res.indexOf('.'));
        }

        return res;
    }

    public static void main(String[] args) throws IOException {

        HttpService httpService = new HttpService(new ObjectMapper());
        GoogleSearcher googleSearcher = new GoogleSearcher(httpService);

        GoogleSearchResult result = googleSearcher.search("cars");

        System.out.println(result);

//        System.out.println(crawler.utils.AppProperties.getInstance().getProperties());
//
//        HttpService httpService = new HttpService();
//        String googleUrl = "https://www.googleapis.com/customsearch/v1?key=AIzaSyD8cAOUT0Yy1pSmUgcauBbRm-1iLyTvHZI&cx=009401593635955574171:7nrrgvnyj_k&q=cars";
//
//        String res = httpService.get(googleUrl);
//
//        System.out.println(res);

//        String googleUrl = "https://www.googleapis.com/customsearch/v1?key=AIzaSyD8cAOUT0Yy1pSmUgcauBbRm-1iLyTvHZI&cx=009401593635955574171:7nrrgvnyj_k&q=cars";
//        String charSet = "UTF-8";
//
//        BufferedReader reader = new BufferedReader(new FileReader("mockGoogleResult.txt"));
//
//        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        GoogleSearchResult searchResult = objectMapper.readValue(new File("mockGoogleResult.txt"), GoogleSearchResult.class);
//
//        String url = "https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js";
//        String res = url.substring(url.lastIndexOf('/') + 1);
//        System.out.println(res.substring(0, res.indexOf('.')));
//
//        HashMap<String, Integer> libraryToCount = new HashMap<>();
//
//        for (GoogleSearchResultItem item : searchResult.getItems()) {
//            System.out.println("Reading: " + item.getLink());
//            try {
//                Document doc = Jsoup.connect(item.getLink()).get();
//
//                List<String> srcAttributes = doc.select("script").stream().map(scriptTag -> scriptTag.attr("src")).
//                        filter(srcAttribute -> !srcAttribute.isEmpty()).collect(Collectors.toList());
//
//                srcAttributes.forEach(attr -> {
//                    String srcAttr = getLibraryName(attr);
//                    libraryToCount.putIfAbsent(srcAttr, -1);
//                    int newCount = libraryToCount.get(srcAttr) + 1;
//                    libraryToCount.put(srcAttr, newCount);
//                });
//
//                System.out.println(srcAttributes);
//            } catch (SocketTimeoutException timeoutException) {
//                System.out.println("Timeout!");
//            }
//        }
//
//        List<Map.Entry<String, Integer>> entries = new ArrayList<>(libraryToCount.entrySet());
//
//        Collections.sort(entries, Comparator.comparing(Map.Entry::getValue));
//
//        System.out.println(">>>>>>>>>>>>>>> RESULTS:");
//        entries.forEach(entry -> System.out.println(entry.getKey() + " -- " + entry.getValue()));
//
//        reader.close();
    }
}
