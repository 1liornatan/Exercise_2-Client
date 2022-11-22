package client;

public class SimpleMain {

    private static final String HOST = "localhost";
    private static final String PORT = "8989";
    private static final String PROTOCOL = "http";

    public static void main(String[] args) {
        Requests req = new Requests(PROTOCOL + "://" + HOST + ":" + PORT);
        req.start();
    }
}
