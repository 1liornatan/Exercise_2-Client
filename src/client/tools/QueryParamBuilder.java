package client.tools;

public class QueryParamBuilder {
    String string;

    public QueryParamBuilder(String key, String value) {
        this.string = ("?" + key + "=" + value);
    }

    public void addQueryParameter(String key, String value) {
        string += ("&" + key + "=" + value);
    }

    @Override
    public String toString() {
        return string;
    }
}
