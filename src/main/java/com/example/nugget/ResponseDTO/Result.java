package com.example.nugget.ResponseDTO;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Result {

    @JsonProperty("audio")
    private String audio;
    @JsonProperty("audio_length_sec")
    private Integer audioLengthSec;
    @JsonProperty("rss")
    private String rss;
    @JsonProperty("description_highlighted")
    private String descriptionHighlighted;
    @JsonProperty("description_original")
    private String descriptionOriginal;
    @JsonProperty("title_highlighted")
    private String titleHighlighted;
    @JsonProperty("title_original")
    private String titleOriginal;
    @JsonProperty("transcripts_highlighted")
    private List<Object> transcriptsHighlighted = null;
    @JsonProperty("image")
    private String image;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("itunes_id")
    private Integer itunesId;
    @JsonProperty("pub_date_ms")
    private Integer pubDateMs;
    @JsonProperty("id")
    private String id;
    @JsonProperty("listennotes_url")
    private String listennotesUrl;
    @JsonProperty("explicit_content")
    private Boolean explicitContent;
    @JsonProperty("link")
    private String link;
    @JsonProperty("podcast")
    private Podcast podcast;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



}
