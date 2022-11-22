package client.tools;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;

public class Constants {
    public static final String URL_BASE = "http://localhost:8989";
    public static final String URL_GET = "/test_get_method";
    public static final String URL_POST = "/test_post_method";
    public static final String URL_PUT = "/test_put_method";
    public static final String URL_DELETE = "/test_delete_method";

    public static final String PARAM_HOUR = "hour";
    public static final String PARAM_MINUTE = "minute";
    public static final String PARAM_ID = "id";

    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    public static final Gson GSON_INSTANCE = new Gson();
}
