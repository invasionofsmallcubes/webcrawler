package capital.scalable;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Page {
    private String link;

    Page(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(link, page.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

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
                return asList(new JSLibrary("a"));
            default:
                return emptyList();
        }
    }
}
