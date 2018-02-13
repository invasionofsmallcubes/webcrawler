package capital.scalable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TopJSLibraries {
    private final GoogleQueryFormatter googleQueryFormatter;
    private final PageRetriever pageRetriever;

    TopJSLibraries(GoogleQueryFormatter googleQueryFormatter, PageRetriever pageRetriever) {
        this.googleQueryFormatter = googleQueryFormatter;
        this.pageRetriever = pageRetriever;
    }

    public List<JSLibrary> fetch(String ... queryParams) {
        String query = googleQueryFormatter.format(queryParams);
        List<Page> pages = pageRetriever.fetch(query);

        List<JSLibrary> jsLibraryStream = pages.stream()
                .map(Page::fetchJSLibraries)
                .flatMap(List::stream)
                .collect(toList());

        return jsLibraryStream;
    }
}
