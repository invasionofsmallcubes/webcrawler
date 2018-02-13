package capital.scalable;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class GoogleQueryFormatter {
    public String format(String... queryParams) {
        return Stream.of(queryParams).collect(joining("+"));
    }
}
