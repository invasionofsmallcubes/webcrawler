package capital.scalable;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class PageRetriever {
    public List<Page> fetch(String query) {
        if (query.equals("") ){
            return emptyList();
        }
        return Arrays.asList(new Page("A"));
    }
}
