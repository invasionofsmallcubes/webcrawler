package capital.scalable.webcrawler.pages;

import capital.scalable.webcrawler.JSLibrary;

import java.util.List;

public interface Page {
    List<JSLibrary> fetchJSLibraries();
}
