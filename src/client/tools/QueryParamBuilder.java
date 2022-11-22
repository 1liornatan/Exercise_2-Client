package client.tools;

public class QueryParamBuilder {
    String string;

    public QueryParamBuilder() {
        this.string = "?";
    }

    public void addQueryParameter(String key, String value) {
        string += (key + "=" + value);
    }

    @Override
    public String toString() {
        return string;
    }
}
