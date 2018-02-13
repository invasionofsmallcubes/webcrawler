package capital.scalable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        return Arrays.asList(new JSLibrary("a"),
                new JSLibrary("b"), new JSLibrary("d"),
                new JSLibrary("e"),new JSLibrary("f"));
    }
}
