package com.example.nugget.model;

import com.example.nugget.repository.Entities.NuggetTaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@DiscriminatorValue("TRIM_PODCAST")
@NoArgsConstructor
public class PodcastTrimTaskRequest extends BaseNuggetTaskRequest {

    @Type(type = "jsonb")
    @Column(name = "task_param", columnDefinition = "jsonb")
    @NotNull(message = "TaskParam cannot be empty")
    private PodcastTrimTaskParam podcastTrimTaskParam;

    @Type(type = "jsonb")
    @Column(name = "podcast_clip_entity", columnDefinition = "jsonb")
    @NotNull(message = "PodcastClipEntity cannot be empty")
    private PodcastClipEntity podcastClipEntity;

    @Override
    public NuggetTaskType getType() {

        return  NuggetTaskType.TRIM_PODCAST;
    }

    @Override
    public UUID getTargetObjectUUID() {
        return this.podcastClipEntity.getId();
    }

    @Override
    public TaskParam getTaskParam() {
        return podcastTrimTaskParam;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "status cannot be empty")
    private NuggetTaskStatus status = NuggetTaskStatus.NOT_STARTED;

    public PodcastTrimTaskRequest(PodcastTrimTaskParam param, PodcastClipEntity podcastClipEntity) {
        this.podcastTrimTaskParam = param;
        this.podcastClipEntity = podcastClipEntity;
    }


}
