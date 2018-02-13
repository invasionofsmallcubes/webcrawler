package capital.scalable.utils;

import capital.scalable.webcrawler.JSoupPage;
import capital.scalable.webcrawler.Page;
import okhttp3.mockwebserver.MockResponse;

import java.io.File;
import java.io.IOException;

import static java.nio.file.Files.readAllBytes;

public class TestUtils {

    public static MockResponse createResponseFromFile(String filename) throws IOException {
        MockResponse response = new MockResponse();
        response.addHeader("content-type", "text/html; charset=UTF-8");
        response.setBody(getBody(filename));
        return response;
    }

    public static String getBody(String filename) throws IOException {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        readAllBytes(file.toPath());
        return new String(readAllBytes(file.toPath()));
    }

    public static Page aPage(String link) {
        return new JSoupPage(link);
    }
}
