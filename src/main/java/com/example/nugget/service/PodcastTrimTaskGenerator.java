package com.example.nugget.service;

import com.example.nugget.ResponseDTO.PodcastTrimRequestDTO;
import com.example.nugget.model.PodcastClipEntity;
import com.example.nugget.model.PodcastTrimTaskParam;
import com.example.nugget.model.PodcastTrimTaskRequest;
import com.example.nugget.repository.PodcastClipEntityRepository;
import com.example.nugget.repository.PodcastClipTaskRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;



@Component
public class PodcastTrimTaskGenerator {

    @Autowired
    PodcastClipTaskRequestRepository taskRequestRepository;

    @Autowired
    PodcastClipEntityRepository podcastClipEntityRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PodcastTrimTaskRequest createTaskRequest(PodcastTrimRequestDTO dto) {

        final PodcastClipEntity podcastClipEntity = this.getPodcastClipEntity(dto);
        PodcastTrimTaskParam param = PodcastTrimTaskParam.fromJson(PodcastTrimRequestDTO.toJson(dto));

        final PodcastTrimTaskRequest podcastTrimTaskRequest =
                new PodcastTrimTaskRequest(param, podcastClipEntity);
        this.taskRequestRepository.save(podcastTrimTaskRequest);
        return podcastTrimTaskRequest;
    }



    private PodcastClipEntity getPodcastClipEntity(PodcastTrimRequestDTO dto) {
        PodcastClipEntity podcastClipEntity = new PodcastClipEntity();
        podcastClipEntity.setPodcastUrl(dto.getPodcastUrl());
        podcastClipEntity.setStartTime(dto.getStartTime());
        podcastClipEntity.setEndTime(dto.getEndTime());
        podcastClipEntity.setImageUrl(dto.getImageUrl());
        podcastClipEntity.setCreated(new Date());
        podcastClipEntityRepository.save(podcastClipEntity);
        return podcastClipEntity;
    }

}
