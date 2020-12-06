package com.example.nugget.repository;

import com.example.nugget.model.PodcastTrimTaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PodcastClipTaskRequestRepository
        extends JpaRepository<PodcastTrimTaskRequest, UUID> {

}
