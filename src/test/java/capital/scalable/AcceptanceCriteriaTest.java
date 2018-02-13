package capital.scalable;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class AcceptanceCriteriaTest {

    @Test
    public void emptyResult() {
        TopJSLibraries topJSLibraries = new TopJSLibraries(new GoogleQueryFormatter(), new PageRetriever());
        List<JSLibrary> top5 = topJSLibraries.fetch();
        assertThat(top5.size(), is(0));
    }

    @Test
    public void withResults() {
        TopJSLibraries topJSLibraries = new TopJSLibraries(new GoogleQueryFormatter(), new PageRetriever());
        List<JSLibrary> top5 = topJSLibraries.fetch("google", "query", "sample");
        assertThat(top5.size(), is(5));
        assertThat(top5, contains(aJSLib("a"),
                aJSLib("b"), aJSLib("d"),
                aJSLib("e"), aJSLib("f")));
    }

    private JSLibrary aJSLib(String name) {
        return new JSLibrary(name);
    }
}
