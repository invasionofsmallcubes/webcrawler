package capital.scalable.webcrawler;

import java.util.Objects;

public class JSLibrary {
    private String libraryName;

    public JSLibrary(String libraryName) {
        this.libraryName = libraryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSLibrary jsLibrary = (JSLibrary) o;
        return Objects.equals(libraryName, jsLibrary.libraryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryName);
    }

    @Override
    public String toString() {
        return "JSLibrary{" +
                "libraryName='" + libraryName + '\'' +
                '}';
    }
}
