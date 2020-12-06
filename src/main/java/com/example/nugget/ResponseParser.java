package com.example.nugget;

import com.example.nugget.ResponseDTO.ResponseObj;
import com.example.nugget.ResponseDTO.ResponsePodcastSearchObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseParser {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static ResponseObj fromJsonResponse(String responseBodyJson) {
        ResponseObj responseObj = gson.fromJson(responseBodyJson, ResponseObj.class);
        return responseObj == null ? null : responseObj;
    }


    public static ResponsePodcastSearchObj fromPodcastSearchJsonResponse(String responseBodyJson) {
        ResponsePodcastSearchObj responseObj = gson.fromJson(responseBodyJson, ResponsePodcastSearchObj.class);
        return responseObj == null ? null : responseObj;
    }
}
