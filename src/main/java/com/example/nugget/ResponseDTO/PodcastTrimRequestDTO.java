package com.example.nugget.ResponseDTO;

import com.google.gson.Gson;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@ToString
public class PodcastTrimRequestDTO {
    private String podcastUrl;
    private String imageUrl;
    private Long startTime ;
    private Long endTime ;

    public static String toJson(final PodcastTrimRequestDTO attribute) {
        final Gson gson = new Gson();
        return gson.toJson(attribute);
    }

    public static PodcastTrimRequestDTO fromJson(final String dbData) {
        final Gson gson = new Gson();
        return gson.fromJson(dbData, PodcastTrimRequestDTO.class);
    }

}
