package capital.scalable;

public class GoogleQueryFormatter {

    public String format(String ... queryParams) {
        StringBuilder result = new StringBuilder();
        if(queryParams.length != 0) {
            for (String queryParam : queryParams) {
                result.append(queryParam).append("+");
            }
            return result.substring(0, result.length()-1);
        }
        else return result.toString();
    }
}
