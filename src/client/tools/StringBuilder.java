package client.tools;

public class StringBuilder {
    String string;

    public StringBuilder() {
        this.string = "?";
    }

    public void addQueryParameter(String key, String value) {
        string += (key + "=" + value);
    }

    public String getString() {
        return string;
    }
}
