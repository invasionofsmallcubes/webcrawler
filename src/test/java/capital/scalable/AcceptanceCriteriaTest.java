package capital.scalable;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@Ignore("will fail until the end")
public class AcceptanceCriteriaTest {

    @Test
    public void emptyResult() {
        TopJSLibraries topJSLibraries = new TopJSLibraries();
        List<JSLibrary> top5 = topJSLibraries.fetch();
        assertThat(top5.size(), is(0));
    }

    @Test
    public void withResults() {
        TopJSLibraries topJSLibraries = new TopJSLibraries();
        List<JSLibrary> top5 = topJSLibraries.fetch("google", "query", "sample");
        assertThat(top5.size(), is(5));
        assertThat(top5, contains(aJSLib("A"),
                aJSLib("B"), aJSLib("C"),
                aJSLib("D"), aJSLib("E"),
                aJSLib("F")));
    }

    private JSLibrary aJSLib(String name) {
        return new JSLibrary(name);
    }
}
