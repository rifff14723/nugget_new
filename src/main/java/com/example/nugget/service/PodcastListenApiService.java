package com.example.nugget.service;

import com.example.nugget.ApiCalls;
import com.example.nugget.ResponseDTO.ResponseObj;
import com.example.nugget.ResponseDTO.ResponsePodcastSearchObj;
import com.example.nugget.ResponseParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PodcastListenApiService {

    public ResponseObj search(String searchString) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("https://listen-api.listennotes.com/api/v2/search?q=chapelle&type=episode")
//                .method("GET", null)
//                .addHeader("X-ListenAPI-Key", "10676b40108f449381e002c25db986f6")
//                //.addHeader("Cookie", "__cfduid=d04435be5f798485924a786de1580533d1605980925")
//                .build();
//        Response response = client.newCall(request).execute();

        try {
            Response response =  ApiCalls.irisApiCall("/search?q=" + searchString+ "&type=episode" , null , "GET") ;
            if (!response.isSuccessful()) {
                return null;
            }
            ResponseObj responseObj = ResponseParser.fromJsonResponse(response.body().string());
            return responseObj ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    public ResponsePodcastSearchObj searchPodcast(String searchString) throws IOException {
        try {
            Response response =  ApiCalls.irisApiCall("/search?q=" + searchString+ "&type=podcast" , null , "GET") ;
            if (!response.isSuccessful()) {
                return null;
            }
            ResponsePodcastSearchObj responseObj = ResponseParser.fromPodcastSearchJsonResponse(response.body().string());
            return responseObj ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }


}
