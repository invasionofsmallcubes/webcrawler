package capital.scalable;

import capital.scalable.webcrawler.GoogleQueryFormatter;
import capital.scalable.webcrawler.JSoupPageRetriever;
import capital.scalable.webcrawler.Top5Strategy;
import capital.scalable.webcrawler.TopJSLibraries;

public class Application {

    public static void main(String[] args) {
        System.out.println("Top 5 Libraries");
        TopJSLibraries topJSLibraries = new TopJSLibraries(new GoogleQueryFormatter(), new JSoupPageRetriever("https://www.google.com/"), new Top5Strategy());
        topJSLibraries.fetch(args).forEach(System.out::println);
    }
}
