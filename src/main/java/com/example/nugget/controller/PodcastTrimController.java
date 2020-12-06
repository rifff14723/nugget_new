package com.example.nugget.controller;

import com.example.nugget.ResponseDTO.PodcastTrimRequestDTO;
import com.example.nugget.service.PodcastTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
public class PodcastTrimController {

    @Autowired
    private PodcastTrimService podcastTrimService  ;

    @RequestMapping(method = RequestMethod.POST, value = "/trim")
    public @ResponseBody ResponseEntity<?>  trimPodcast(@RequestBody PodcastTrimRequestDTO podcastTrimDTO) {
        UUID taskUUID = podcastTrimService.trim(podcastTrimDTO) ;
        return ResponseEntity.ok(taskUUID);
    }

    //TODO: Download Snippet

    //TODO: Play a snippet
}
