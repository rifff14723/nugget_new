package com.example.nugget;

import com.google.gson.JsonObject;
import okhttp3.*;


public class ApiCalls {

    protected static  OkHttpClient client = new OkHttpClient().newBuilder().build();
    private static String listenNotesApiBaseUrl = "https://listen-api.listennotes.com/api/v2";
    private static String searchString = "searchString";


    public static Response irisApiCall(String url, JsonObject testBody, String method) throws Exception {

        MediaType mediaType = MediaType.parse("application/json");
        String api = formulateAPIUrl(url);
        Request.Builder builder = new Request.Builder().url(listenNotesApiBaseUrl + api)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-ListenAPI-Key", "10676b40108f449381e002c25db986f6");
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
            String testBodyStr = testBody == null ? "" : testBody.toString();
            RequestBody body = RequestBody.create(mediaType, testBodyStr);
            builder.method(method, body);

        } else if ("DELETE".equalsIgnoreCase(method)) {
            builder.method(method, RequestBody.create(null, new byte[0]));
        }

        Request request = builder.build();

        Response response = client.newCall(request).execute();

        return response;
    }

    public static String formulateAPIUrl(String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            return url;
        }
        String api = replaceUrlTokens(url, "{" + searchString + "}", "chapelle");
        return api;
    }

    public static String replaceUrlTokens(String url, String token, String value) {
        if (url == null || token == null || value == null) {
            return url;
        }
        return url.replace(token, value);
    }


}
