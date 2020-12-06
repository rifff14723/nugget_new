package com.example.nugget.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class ResultPodcastSearch {

    @JsonProperty("rss")
    private String rss;

    @JsonProperty("description_highlighted")
    private String description_highlighted;

    @JsonProperty("description_original")
    private String description_original;

    @JsonProperty("title_highlighted")
    private String title_highlighted;

    @JsonProperty("title_original")
    private String title_original;

    @JsonProperty("publisher_highlighted")
    private String publisher_highlighted;

    @JsonProperty("publisher_original")
    private String publisher_original;

    @JsonProperty("image")
    private String image;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("itunes_id")
    private Long itunes_id;

    @JsonProperty("latest_pub_date_ms")
    private Long latest_pub_date_ms;

    @JsonProperty("earliest_pub_date_ms")
    private Long earliest_pub_date_ms;

    @JsonProperty("id")
    private String id;

    @JsonProperty("genre_ids")
    private List<Integer> genre_ids = null;

    @JsonProperty("listennotes_url")
    private String listennotes_url;

    @JsonProperty("total_episodes")
    private Integer total_episodes;

    @JsonProperty("email")
    private String email;

    @JsonProperty("explicit_content")
    private Boolean explicit_content;

    @JsonProperty("website")
    private Object website;

    @JsonProperty("listen_score")
    private String listen_score;

    @JsonProperty("listen_score_global_rank")
    private String listen_score_global_rank;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
