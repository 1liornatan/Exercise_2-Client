package client;

import client.dto.PostData;
import client.dto.PutData;
import client.dto.ResponseMessage;
import client.tools.Constants;
import client.tools.QueryParamBuilder;
import client.tools.Time;
import okhttp3.*;

import java.io.IOException;


public class Requests {
    private final String baseURL;
    private Time time;
    private String putResponse;
    private String postResponse;
    private String getResponse;

    public Requests(String url) {
        this.baseURL = url;
    }

    private void get() throws IOException {

        time = new Time();

        QueryParamBuilder param = new QueryParamBuilder(Constants.PARAM_HOUR, time.getHour().toString());
        param.addQueryParameter(Constants.PARAM_MINUTE, time.getMinute().toString());

        Request request = new Request.Builder().url(baseURL + Constants.URL_GET + param).build();
        Response response = Constants.HTTP_CLIENT.newCall(request).execute();
        ResponseBody responseBody = response.body();
        if(responseBody != null) {
            getResponse = responseBody.string();
            responseBody.close();
        }
    }

    private void post() throws IOException {
        PostData data = new PostData(time.getHour(), time.getMinute(), getResponse);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(Constants.GSON_INSTANCE.toJson(data), mediaType);
        Request request = new Request.Builder()
                .url(baseURL + Constants.URL_POST)
                .method("POST", requestBody)
                .build();

        Response response = Constants.HTTP_CLIENT.newCall(request).execute();
        postResponse = getResponseMessage(response);
    }

    private String getResponseMessage(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        String message = "";
        if(responseBody != null) {
            ResponseMessage messageData = Constants.GSON_INSTANCE.fromJson(responseBody.string(), ResponseMessage.class);
            message = messageData.getMessage();
            responseBody.close();
        }
        return message;
    }

    private void put() throws IOException {
        PutData data = new PutData((time.getHour() + 21) % 24, (time.getMinute() + 13) % 60);
        QueryParamBuilder param = new QueryParamBuilder(Constants.PARAM_ID, postResponse);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(Constants.GSON_INSTANCE.toJson(data), mediaType);
        Request request = new Request.Builder()
                .url(baseURL + Constants.URL_PUT + param)
                .method("PUT", requestBody)
                .build();
        Response response = Constants.HTTP_CLIENT.newCall(request).execute();
        putResponse = getResponseMessage(response);
    }

    private void delete() throws IOException {
        QueryParamBuilder param = new QueryParamBuilder(Constants.PARAM_ID, putResponse);

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody requestBody = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(baseURL + Constants.URL_DELETE + param)
                .method("DELETE", requestBody)
                .build();
        Response response = Constants.HTTP_CLIENT.newCall(request).execute();
        if(response.isSuccessful())
            System.out.println("Successful!");
    }

    public void start() {
        try {
            System.out.println("Sending Get Request..");
            get();
            System.out.println("Sending Post request..");
            post();
            System.out.println("Sending Put request..");
            put();
            System.out.println("Sending Delete request..");
            delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
