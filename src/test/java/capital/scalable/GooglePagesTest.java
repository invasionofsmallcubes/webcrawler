package capital.scalable;

import capital.scalable.fake.FakePage;
import capital.scalable.fake.FakePageRetriever;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;


public class GooglePagesTest {

    @Test
    public void emptyResult() {
        FakePageRetriever pageRetriever = new FakePageRetriever();
        List<Page> pages = pageRetriever.fetch("");
        assertThat(pages.size(), is(0));
    }

    @Test
    public void someResult() {
        FakePageRetriever pageRetriever = new FakePageRetriever();
        List<Page> pages = pageRetriever.fetch("my+query");
        assertThat(pages, contains(aPage("A")));
    }

    private Page aPage(String link) {
        return new FakePage(link);
    }
}
