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
public class ResponsePodcastSearchObj {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next_offset")
    private Integer nextOffset;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("took")
    private Double took;
    @JsonProperty("results")
    private List<ResultPodcastSearch> results = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
