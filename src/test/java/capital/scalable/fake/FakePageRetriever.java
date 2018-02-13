package capital.scalable.fake;

import capital.scalable.webcrawler.Page;
import capital.scalable.webcrawler.PageRetriever;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class FakePageRetriever implements PageRetriever {
    @Override
    public List<Page> fetch(String query) {
        if (query.equals("") ){
            return emptyList();
        }
        return Arrays.asList(new FakePage("A"));
    }
}
