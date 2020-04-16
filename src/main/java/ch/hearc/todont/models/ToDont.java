package ch.hearc.todont.models;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "todonts")
public class ToDont {

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
    private User owner;

    @ManyToMany
    private Set<User> moderators;

    @ManyToMany
    private Set<User> participants;

    @ManyToMany
    private Set<Comment> comments;

    private String name;

    @Nullable
    private String description;

    private Timestamp publicationDate;

    private boolean closed;

    @Enumerated(EnumType.ORDINAL)
    private Visibility visibility;

    @Nullable
    private String rewards;

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

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
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

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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
}