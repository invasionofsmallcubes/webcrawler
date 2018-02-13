package capital.scalable.webcrawler.pages.jsoup;

import capital.scalable.webcrawler.pages.Page;
import capital.scalable.webcrawler.pages.PageRetriever;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.jsoup.Jsoup.connect;

public class JSoupPageRetriever implements PageRetriever {

    private final String host;

    public JSoupPageRetriever(String host) {
        this.host = host;
    }

    @Override
    public List<Page> fetch(String query) {
        try {
            Document document = connect(host + "search?q=" + query).get();
            Elements pages = document.select(".r a");
            return pages.stream()
                    .map(p -> new JSoupPage(p.attr("href")))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }
}
