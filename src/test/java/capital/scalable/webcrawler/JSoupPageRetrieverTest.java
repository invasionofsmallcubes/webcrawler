package capital.scalable.webcrawler;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static capital.scalable.utils.TestUtils.aPage;
import static capital.scalable.utils.TestUtils.createResponseFromFile;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;


public class JSoupPageRetrieverTest {

    private MockWebServer mockWebServer;

    private final PageRetriever pageRetriever = new JSoupPageRetriever("http://localhost:8087/");

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void emptyResult() throws IOException, InterruptedException {
        mockWebServer.enqueue(createResponseFromFile("emptyResult.txt"));
        mockWebServer.start(8087);

        List<Page> pages = pageRetriever.fetch("");

        assertThat(mockWebServer.takeRequest().getPath(), is("/search?q="));

        assertThat(pages.size(), is(0));
    }

    @Test
    public void someResult() throws IOException, InterruptedException {

        mockWebServer.enqueue(createResponseFromFile("resultWithPages.txt"));
        mockWebServer.start(8087);

        List<Page> pages = pageRetriever.fetch("fancy+restaurant");

        assertThat(mockWebServer.takeRequest().getPath(), is("/search?q=fancy+restaurant"));

        assertThat(pages, contains(aPage("http://context.reverso.net/traduzione/inglese-italiano/fancy+restaurant"),
                aPage("http://context.reverso.net/traduzione/inglese-italiano/a+fancy+restaurant"),
                aPage("http://www.businessinsider.com/best-tips-for-eating-at-fancy-restaurants-2016-8"),
                aPage("https://www.entrepreneur.com/article/233856"),
                aPage("http://www.thisisinsider.com/restaurant-etiquette-tips-2017-6"),
                aPage("https://munchies.vice.com/en_us/article/3d4bzw/the-21-best-fancy-restaurants-in-la"),
                aPage("https://en.wikipedia.org/wiki/Types_of_restaurant"),
                aPage("https://www.tripadvisor.it/LocationPhotoDirectLink-g291978-d2469322-i38069141-Paniscea_Restaurant-Orange_Walk_Orange_Walk_District.html"),
                aPage("https://www.tripadvisor.com/Restaurant_Review-g52206-d518213-Reviews-Plain_Fancy-Bird_in_Hand_Lancaster_County_Pennsylvania.html")));
    }


}
