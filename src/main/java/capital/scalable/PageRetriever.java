package capital.scalable;

import java.util.List;

public interface PageRetriever {
    List<Page> fetch(String query);
}
