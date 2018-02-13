package capital.scalable.webcrawler;

import capital.scalable.webcrawler.GoogleQueryFormatter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GoogleQueryFormatterTest {

    private final GoogleQueryFormatter googleQueryFormatter = new GoogleQueryFormatter();

    @Test
    public void formatEmptyString() {
        String query = googleQueryFormatter.format();
        assertThat(query, is(""));
    }

    @Test
    public void formatOneString() {
        String query = googleQueryFormatter.format("oneString");
        assertThat(query, is("oneString"));
    }

    @Test
    public void formatNStrings() {
        String query = googleQueryFormatter.format("oneString", "twoString");
        assertThat(query, is("oneString+twoString"));
    }
}
