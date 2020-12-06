package com.example.nugget.model;

import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class PodcastTrimTaskParam extends TaskParam {

    @NotEmpty
    private String podcastUrl;
    @NotEmpty
    private String imageUrl;
    @NotEmpty
    private Long startTime ;
    @NotEmpty
    private Long endTime ;



    public static String toJson(final PodcastTrimTaskParam attribute) {
        final Gson gson = new Gson();
        final String json = gson.toJson(attribute);
        return json;
    }

    public static PodcastTrimTaskParam fromJson(final String dbData) {
        final Gson gson = new Gson();
        final PodcastTrimTaskParam dc = gson.fromJson(dbData, PodcastTrimTaskParam.class);
        return dc;
    }
}
