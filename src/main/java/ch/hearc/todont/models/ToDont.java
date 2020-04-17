package ch.hearc.todont.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "todonts")
public class ToDont {

    // FIELDS

    public enum Visibility {
        PUBLIC,
        UNLISTED,
        PRIVATE
    }

    // We use UUIDs for the ToDont mainly so that the
    // unlisted ToDonts' URL can't be guessed
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(
        name = "toDontId",
        nullable = false
    )
    private User owner;

    @ManyToMany
    @JoinTable(
        name = "toDontModerators",
        joinColumns = @JoinColumn(name = "toDontId"),
        inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<User> moderators;

    @OneToMany(mappedBy = "toDont")
    private Set<Pledge> pledges;

    @OneToMany(mappedBy = "toDont")
    private Set<Comment> comments;

    @Column(
        name = "name",
        length = 255,
        nullable = false
    )
    private String name;

    @Column(
        name = "description",
        length = 255,
        nullable = true
    )
    private String description;

    @Column(
        name = "datePublished",
        nullable = false
    )
    private Timestamp datePublished;

    @Column(
        name = "dateClosed",
        nullable = true
    )
    private Timestamp dateClosed;

    @Enumerated(EnumType.ORDINAL)
    private Visibility visibility;

    @Column(
        name = "rewards",
        length = 255,
        nullable = true
    )
    private String rewards;

    // METHODS

    /**
     * Set the closed date to now
     */
    public void close() {
        setDateClosed(Timestamp.from(Instant.now()));
    }

    /**
     * Whether the ToDont is already closed
     */
    public boolean isClosed() {
        if (dateClosed == null) {
            return false;
        }
        return Timestamp.from(Instant.now()).after(dateClosed);
    }

    // CONSTRUCTOR AND GETTERS-SETTERS

    public ToDont() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getModerators() {
        return moderators;
    }

    public void setModerators(Set<User> moderators) {
        this.moderators = moderators;
    }

    public Set<Pledge> getPledges() {
        return pledges;
    }

    public void setPledges(Set<Pledge> pledges) {
        this.pledges = pledges;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public Timestamp getDatePublished() {
        return datePublished;
    }

    public Timestamp getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Timestamp dateClosed) {
        this.dateClosed = dateClosed;
    }
}