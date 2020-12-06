package com.example.nugget.service;

import com.example.nugget.model.PodcastTrimTaskRequest;
import com.example.nugget.repository.Entities.NuggetTaskStatus;
import com.example.nugget.repository.PodcastClipTaskRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public class TaskService {

    @Autowired
    PodcastClipTaskRequestRepository taskRepository ;


    public List<PodcastTrimTaskRequest> getAllTasks()  {
        List<PodcastTrimTaskRequest> nuggetTaskEntityList = this.taskRepository.findAll() ;
        return nuggetTaskEntityList ;
    }

    public PodcastTrimTaskRequest getTaskByUUID(UUID uuid)  {
        Optional<PodcastTrimTaskRequest> nuggetTaskEntity = this.taskRepository.findById(uuid) ;
        return nuggetTaskEntity.get() ;
    }

    public PodcastTrimTaskRequest changeTaskStatus(UUID uuid, String taskStatus)  {
        Optional<PodcastTrimTaskRequest> nuggetTaskEntityOp = this.taskRepository.findById(uuid) ;
        PodcastTrimTaskRequest podcastTrimTaskRequest  = nuggetTaskEntityOp.get() ;
        if(taskStatus.equals("FAILED")){
            podcastTrimTaskRequest.setStatus(NuggetTaskStatus.FAILED);
        }
        else if(taskStatus.equals("SUCCESS")) {
            podcastTrimTaskRequest.setStatus(NuggetTaskStatus.SUCCESS);
        }
        taskRepository.save(podcastTrimTaskRequest) ;
        return podcastTrimTaskRequest ;
    }

    public PodcastTrimTaskRequest getPodcastClipFromTaskUUID(UUID uuid)  {
        Optional<PodcastTrimTaskRequest> nuggetTaskEntity = this.taskRepository.findById(uuid) ;
        //nuggetTaskEntity.get().getPodcast() ;
        return nuggetTaskEntity.get() ;
    }
}
