package capital.scalable.webcrawler.pages;

import java.util.List;

public interface PageRetriever {
    List<Page> fetch(String query);
}
