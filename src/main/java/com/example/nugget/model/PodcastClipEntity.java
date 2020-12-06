package com.example.nugget.model;



import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDefs({ @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })
public class PodcastClipEntity extends BaseEntity {

    private String podcastUrl;
    private Long startTime ;
    private Long endTime ;
    private String imageUrl;

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

}
