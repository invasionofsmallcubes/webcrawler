package capital.scalable.webcrawler;

import capital.scalable.webcrawler.pages.jsoup.JSoupPage;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static capital.scalable.utils.TestUtils.createResponseFromFile;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JSoupPageTest {

    private MockWebServer mockWebServer;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void getLibraries() throws IOException, InterruptedException {
        mockWebServer.enqueue(createResponseFromFile("pageResult.txt"));
        mockWebServer.start(8087);
        JSoupPage jSoupPage = new JSoupPage("http://localhost:8087/myLink");
        List<JSLibrary> jsLibraries = jSoupPage.fetchJSLibraries();
        assertThat(mockWebServer.takeRequest().getPath(), is("/myLink"));
        assertThat(jsLibraries.size(), is(6));

    }
}
