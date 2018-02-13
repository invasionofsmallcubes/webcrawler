package capital.scalable;

import capital.scalable.webcrawler.*;

import static java.lang.System.out;

public class Application {

    public static void main(String[] args) {
        try {
            out.println("Top 5 Libraries");
            TopJSLibraries topJSLibraries = new TopJSLibraries(new GoogleQueryFormatter(), new JSoupPageRetriever("https://www.google.com/"), new Top5Strategy());
            topJSLibraries.fetch(args).forEach(out::println);
        } catch (FetchInterrupted e) {
            out.println("There were issues fetching the results");
        }
    }
}
