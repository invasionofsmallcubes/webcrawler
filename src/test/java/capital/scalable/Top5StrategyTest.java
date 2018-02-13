package capital.scalable;

import capital.scalable.webcrawler.JSLibrary;
import capital.scalable.webcrawler.Top5Strategy;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;


import static java.util.stream.Stream.of;

public class Top5StrategyTest {

    @Test
    public void top5Strategy() {
        Stream<JSLibrary> jsLibraryStream = of(jsLib("A"), jsLib("A"),
                jsLib("A"), jsLib("A"),
                jsLib("A"), jsLib("B"),
                jsLib("B"), jsLib("B"),
                jsLib("C"), jsLib("C"),
                jsLib("D"), jsLib("E"),
                jsLib("T"), jsLib("T"));

        List<JSLibrary> top5 = new Top5Strategy().extract(jsLibraryStream);
        assertThat(top5.size(), is(5));
        assertThat(top5, contains(jsLib("A"), jsLib("B"), jsLib("C"), jsLib("T"),jsLib("D")));
    }

    @Test
    public void allEquals() {
        Stream<JSLibrary> jsLibraryStream = of(jsLib("A"),
                jsLib("B"), jsLib("C"),
                jsLib("D"), jsLib("E"),
                jsLib("T"));

        List<JSLibrary> top5 = new Top5Strategy().extract(jsLibraryStream);
        assertThat(top5.size(), is(5));
        assertThat(top5, contains(jsLib("A"), jsLib("B"), jsLib("C"), jsLib("T"),jsLib("D")));
    }

    private JSLibrary jsLib(String a) {
        return new JSLibrary(a);
    }
}
