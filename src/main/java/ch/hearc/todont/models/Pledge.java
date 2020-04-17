package ch.hearc.todont.models;

import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * This contains the additional information in the relation between a user and a
 * ToDont he's pledged to follow.
 */
@Entity
@Table(name = "pledges")
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

    @Column(name = "dateJoined", nullable = false)
    private Timestamp dateJoined;

    @Column(name = "dateFailed", nullable = true)
    private Timestamp dateFailed;

    /**
     * Default constructor
     */
    public Pledge() {}

    /**
     * Basic constructor
     * 
     * @param user User that will pledge to the ToDont
     * @param toDont ToDont the user will be pledged to
     * @return The newly created Pledge object
     */
    public Pledge(User user, ToDont toDont) {
        this();

        setUser(user);
        setToDont(toDont);
        setDateJoined(Timestamp.from(Instant.now()));
    }

    /**
     * Whether the ToDont has been done or not
     * 
     * @return True if the user has failed the ToDont
     */
    public boolean isFailed() {
        if (dateFailed == null) {
            return false;
        }
        return Timestamp.from(Instant.now()).after(dateFailed);
    }

    /**
     * Indicate that the user has failed the ToDont
     */
    public void fail() {
        setDateFailed(Timestamp.from(Instant.now()));
    }

    public PledgeKey getId() {
        return id;
    }

    public void setId(PledgeKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ToDont getToDont() {
        return toDont;
    }

    public void setToDont(ToDont toDont) {
        this.toDont = toDont;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Timestamp getDateFailed() {
        return dateFailed;
    }

    public void setDateFailed(Timestamp dateFailed) {
        this.dateFailed = dateFailed;
    }
}