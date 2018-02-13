package capital.scalable.webcrawler;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.sort;
import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Top5Strategy {

    public List<JSLibrary> extract(Stream<JSLibrary> jsLibraryStream) {
        Map<JSLibrary, Long> librariesWithCount = groupByNameAndCount(jsLibraryStream);
        List<Result> sortedList = sortByCount(librariesWithCount);
        return top5(sortedList);
    }

    private List<JSLibrary> top5(List<Result> sortedList) {
        return sortedList.stream().limit(5).map(x -> x.library).collect(toList());
    }

    private List<Result> sortByCount(Map<JSLibrary, Long> librariesWithCount) {
        List<Result> result = new LinkedList<>();
        librariesWithCount.forEach((key, value) -> result.add(new Result(value, key)));
        sort(result, reverseOrder());
        return result;
    }

    private Map<JSLibrary, Long> groupByNameAndCount(Stream<JSLibrary> jsLibraryStream) {
        return jsLibraryStream
                .collect(groupingBy(identity(), counting()));
    }

    class Result implements Comparable<Result> {
        private final Long occurrence;
        private final JSLibrary library;

        Result(Long occurrence, JSLibrary library) {
            this.occurrence = occurrence;
            this.library = library;
        }

        @Override
        public int compareTo(Result other) {
            return occurrence.intValue() - other.occurrence.intValue();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return Objects.equals(library, result.library);
        }

        @Override
        public int hashCode() {

            return Objects.hash(library);
        }
    }
}
