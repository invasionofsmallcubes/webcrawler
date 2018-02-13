package capital.scalable.webcrawler.usecase;

import capital.scalable.webcrawler.*;
import capital.scalable.webcrawler.pages.Page;
import capital.scalable.webcrawler.pages.PageRetriever;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;

public class TopJSLibraries {
    private static final int THREADS = 10;
    private final GoogleQueryFormatter googleQueryFormatter;
    private final PageRetriever pageRetriever;
    private Top5Strategy top5Strategy;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS);

    public TopJSLibraries(GoogleQueryFormatter googleQueryFormatter, PageRetriever pageRetriever, Top5Strategy top5Strategy) {
        this.googleQueryFormatter = googleQueryFormatter;
        this.pageRetriever = pageRetriever;
        this.top5Strategy = top5Strategy;
    }

    public List<JSLibrary> fetch(String... queryParams) {

        String query = googleQueryFormatter.format(queryParams);
        List<Page> pages = pageRetriever.fetch(query);

        CompletableFuture<List<List<JSLibrary>>> listCompletableFuture = fetchLibraries(pages);

        try {
            Stream<JSLibrary> allJsLibraries = listCompletableFuture.get().stream().flatMap(List::stream);
            List<JSLibrary> result = top5Strategy.extract(allJsLibraries);
            executor.shutdown();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new FetchInterrupted();
        }

    }

    private CompletableFuture<List<List<JSLibrary>>> fetchLibraries(List<Page> pages) {
        List<CompletableFuture<List<JSLibrary>>> futures = pages.stream()
                .map(page -> supplyAsync(page::fetchJSLibraries, executor))
                .collect(toList());

        return allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(toList()));
    }
}
