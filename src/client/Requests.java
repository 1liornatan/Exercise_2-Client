package client;

import client.dto.PostData;
import client.dto.PutData;
import client.dto.ResponseMessage;
import client.tools.Constants;
import client.tools.StringBuilder;
import client.tools.Time;
import okhttp3.*;

import java.io.IOException;
import java.util.Date;


public class Requests {
    private final String baseURL;
    private String putResponse;
    private String postResponse;
    private String getResponse;

    public Requests(String url) {
        this.baseURL = url;
    }

    public void get() {
        StringBuilder param = new StringBuilder();
        Time time = new Time();

        param.addQueryParameter(Constants.PARAM_HOUR, time.getHour().toString());
        param.addQueryParameter(Constants.PARAM_MINUTE, time.getMinute().toString());

        Request request = new Request.Builder().url(baseURL + Constants.URL_GET + param).build();
        try {
            System.out.println("Sending Get Request..");
            Response response = Constants.HTTP_CLIENT.newCall(request).execute();
            getResponse = response.toString();
            System.out.println(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void post() {
        Time time = new Time();
        PostData data = new PostData(time.getHour(), time.getMinute(), getResponse);

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, Constants.GSON_INSTANCE.toJson(data));
        Request request = new Request.Builder()
                .url(baseURL + Constants.URL_POST)
                .method("POST", body)
                .build();
        try {
            System.out.println("Sending Post request..");
            Response response = Constants.HTTP_CLIENT.newCall(request).execute();
            postResponse = response.toString();
            System.out.printf(response.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void put() {
        Time time = new Time();
        PutData data = new PutData((time.getHour() + 21) % 24, (time.getMinute() + 13) % 60);
        StringBuilder param = new StringBuilder();

        param.addQueryParameter(Constants.PARAM_ID, postResponse);

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, Constants.GSON_INSTANCE.toJson(data));
        Request request = new Request.Builder()
                .url(baseURL + Constants.URL_PUT + param)
                .method("PUT", body)
                .build();
        try {
            System.out.println("Sending Put request..");
            Response response = Constants.HTTP_CLIENT.newCall(request).execute();
            ResponseMessage messageData = Constants.GSON_INSTANCE.fromJson(response.toString(), ResponseMessage.class);
            putResponse = messageData.getMessage();
            System.out.println(response.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        StringBuilder param = new StringBuilder();
        param.addQueryParameter(Constants.PARAM_ID, putResponse);

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(baseURL + Constants.URL_DELETE + param)
                .method("DELETE", body)
                .build();
        try {
            System.out.println("Sending Delete request..");
            Response response = Constants.HTTP_CLIENT.newCall(request).execute();
            System.out.println(response.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
