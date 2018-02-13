package capital.scalable;

import java.util.List;
import java.util.stream.Stream;

public class TopJSLibraries {
    private final GoogleQueryFormatter googleQueryFormatter;
    private final PageRetriever pageRetriever;
    private Top5Strategy top5Strategy;

    TopJSLibraries(GoogleQueryFormatter googleQueryFormatter, PageRetriever pageRetriever, Top5Strategy top5Strategy) {
        this.googleQueryFormatter = googleQueryFormatter;
        this.pageRetriever = pageRetriever;
        this.top5Strategy = top5Strategy;
    }

    public List<JSLibrary> fetch(String... queryParams) {

        String query = googleQueryFormatter.format(queryParams);
        List<Page> pages = pageRetriever.fetch(query);

        Stream<JSLibrary> allJsLibraries = pages.stream()
                .map(Page::fetchJSLibraries)
                .flatMap(List::stream);

        List<JSLibrary> result = top5Strategy.extract(allJsLibraries);

        return result;
    }
}
