package client;

import client.tools.Constants;

public class SimpleMain {

    public static void main(String[] args) {
        Requests req = new Requests(Constants.URL_BASE);
        req.start();
    }
}
