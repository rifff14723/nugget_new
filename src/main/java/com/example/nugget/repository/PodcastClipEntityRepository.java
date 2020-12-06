package com.example.nugget.repository;

import com.example.nugget.model.PodcastClipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface PodcastClipEntityRepository extends JpaRepository<PodcastClipEntity, UUID> {

}
