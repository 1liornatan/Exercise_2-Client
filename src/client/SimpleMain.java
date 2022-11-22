package client;

public class SimpleMain {

    public static void main(String[] args) {
        Requests req = new Requests("localhost:8989");
        req.start();
    }
}
