package capital.scalable.webcrawler.pages.jsoup;

import capital.scalable.webcrawler.JSLibrary;
import capital.scalable.webcrawler.pages.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class JSoupPage implements Page {

    private String link;

    public JSoupPage(String link) {
        this.link = link;
    }

    @Override
    public List<JSLibrary> fetchJSLibraries() {
        try {
            Document document = Jsoup.connect(link).get();
            Elements scripts = document.select("script");
            return scripts.stream()
                    .filter( p -> !p.attr("src").equals(""))
                    .map(p -> new JSLibrary(p.attr("src")))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSoupPage jSoupPage = (JSoupPage) o;
        return Objects.equals(link, jSoupPage.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public String toString() {
        return "JSoupPage{" +
                "link='" + link + '\'' +
                '}';
    }
}
