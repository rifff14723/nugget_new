package com.example.nugget.controller;

import com.example.nugget.ResponseDTO.ResponseObj;
import com.example.nugget.ResponseDTO.ResponsePodcastSearchObj;
import com.example.nugget.service.PodcastListenApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@CrossOrigin
public class PodcastListenApiController {

    @Autowired
    private PodcastListenApiService podcastListenApiService  ;

    @RequestMapping(method = RequestMethod.GET, value = "/searchEpisode")
    public @ResponseBody ResponseEntity<?>  searchEpisode(@RequestParam(name = "searchString") final String searchString) throws IOException {
        ResponseObj responseObj = podcastListenApiService.search(searchString) ;
        return ResponseEntity.ok(responseObj) ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchPodcast")
    public @ResponseBody ResponseEntity<?>  searchPodcast(@RequestParam(name = "searchString") final String searchPodcastString) throws IOException {
        ResponsePodcastSearchObj responseObj = podcastListenApiService.searchPodcast(searchPodcastString) ;
        return ResponseEntity.ok(responseObj) ;
    }
}
