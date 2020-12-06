package com.example.nugget.ResponseDTO;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
public class Podcast {

    private String listennotesUrl;
    @JsonProperty("id")
    private String id;
    @JsonProperty("title_highlighted")
    private String titleHighlighted;
    @JsonProperty("title_original")
    private String titleOriginal;
    @JsonProperty("publisher_highlighted")
    private String publisherHighlighted;
    @JsonProperty("publisher_original")
    private String publisherOriginal;
    @JsonProperty("image")
    private String image;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds = null;
    @JsonProperty("listen_score")
    private String listenScore;
    @JsonProperty("listen_score_global_rank")
    private String listenScoreGlobalRank;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}

