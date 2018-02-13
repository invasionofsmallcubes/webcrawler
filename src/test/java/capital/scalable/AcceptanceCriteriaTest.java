package capital.scalable;

import capital.scalable.fake.FakePageRetriever;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class AcceptanceCriteriaTest {

    private final TopJSLibraries topJSLibraries = new TopJSLibraries(new GoogleQueryFormatter(),
            new FakePageRetriever(), new Top5Strategy());

    @Test
    public void emptyResult() {
        List<JSLibrary> top5 = topJSLibraries.fetch();
        assertThat(top5.size(), is(0));
    }

    @Test
    public void withResults() {
        List<JSLibrary> top5 = topJSLibraries.fetch("google", "query", "sample");
        assertThat(top5.size(), is(5));
        assertThat(top5, contains(aJSLib("a"),
                aJSLib("b"), aJSLib("c"),
                aJSLib("d"), aJSLib("e")));
    }

    private JSLibrary aJSLib(String name) {
        return new JSLibrary(name);
    }
}
