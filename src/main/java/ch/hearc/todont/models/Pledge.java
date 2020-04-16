package ch.hearc.todont.models;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.lang.Nullable;

/**
 * This contains the additional information in the relation
 * between a user and a ToDont he's pledged to follow
 */
@Entity
public class Pledge {
    
    @EmbeddedId
    private PledgeKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @MapsId("toDontId")
    @JoinColumn(name = "toDontId")
    private ToDont toDont;

    private Timestamp dateJoined;

    @Nullable
    private Timestamp dateFailed;

}