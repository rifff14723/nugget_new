package com.example.nugget.repository.Entities;

import com.example.nugget.model.BaseNuggetTaskRequest;
import com.example.nugget.model.NuggetTaskType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "NUGGET_TASK")
public class NuggetTaskEntity  {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "task_id", updatable = false, nullable = false)
    private UUID taskId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "status cannot be empty")
    private NuggetTaskStatus status = NuggetTaskStatus.NOT_STARTED;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "req_id", nullable = false)
    @NotNull(message = "baseNuggetTaskRequest cannot be empty")
    private BaseNuggetTaskRequest baseNuggetTaskRequest;

    @Column(name = "created_time", nullable = false)
    @NotNull(message = "createdTimestamp cannot be empty")
    private Timestamp createdTimestamp;

    @Column(name = "completed_time", nullable = true)
    private Timestamp completedTimestamp;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "nuggetTaskType cannot be empty")
    @Column(name = "nuggetTaskType", nullable = false)
    private NuggetTaskType nuggetTaskType;

     @NotNull(message = "user cannot be empty")
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "user_id")
     private UserEntity user;

    @Column(name = "reconciled", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isReconciled;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "initiated_by")
    private String initiatedBy;

    public UUID getTaskId() {
        return this.taskId;
    }


    public UUID getTargetObjectUUID() {
        return this.baseNuggetTaskRequest.getTargetObjectUUID();
    }

}
