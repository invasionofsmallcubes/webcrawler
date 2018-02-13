package capital.scalable.fake;

import capital.scalable.webcrawler.JSLibrary;
import capital.scalable.webcrawler.pages.Page;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class FakePage implements Page {
    private String link;

    public FakePage(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakePage page = (FakePage) o;
        return Objects.equals(link, page.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public List<JSLibrary> fetchJSLibraries() {

        switch (link) {
            case "A":
                return asList(new JSLibrary("a"),
                        new JSLibrary("b"), new JSLibrary("c"),
                        new JSLibrary("d"), new JSLibrary("e"));
            case "B":
                return asList(new JSLibrary("a"),
                        new JSLibrary("b"), new JSLibrary("c"),
                        new JSLibrary("d"));
            case "C":
                return asList(new JSLibrary("a"),
                        new JSLibrary("b"), new JSLibrary("c"));
            case "D":
                return asList(new JSLibrary("a"),
                        new JSLibrary("b"));
            case "E":
                return singletonList(new JSLibrary("a"));
            default:
                return emptyList();
        }
    }
}
