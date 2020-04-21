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
     * Default constructor.
     */
    public Pledge() { 
        id = new PledgeKey();
    }

    /**
     * Basic constructor.
     * 
     * @param user User that will pledge to the ToDont.
     * @param toDont ToDont the user will be pledged to.
     */
    public Pledge(User user, ToDont toDont) {
        this();

        setUser(user);
        setToDont(toDont);
        setDateJoined(Timestamp.from(Instant.now()));
    }

    /**
     * Whether the ToDont has been done or not.
     * 
     * @return True if the user has failed the ToDont.
     */
    public boolean isFailed() {
        if (dateFailed == null) {
            return false;
        }
        return !Timestamp.from(Instant.now()).before(dateFailed);
    }

    /**
     * Indicate that the user has failed the ToDont.
     */
    public void fail() {
        setDateFailed(Timestamp.from(Instant.now()));
    }

    public PledgeKey getId() {
        return id;
    }

    public void setId(PledgeKey id) {
        System.out.println("Setting pledgeKey to " + id);
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

    @Override
    public String toString() {
        return "Pledge [dateFailed=" + dateFailed + ", dateJoined=" + dateJoined + ", id=" + id + ", toDont=" + toDont
                + ", user=" + user + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pledge other = (Pledge) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}