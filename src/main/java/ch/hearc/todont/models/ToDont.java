package ch.hearc.todont.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
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
        name = "userId",
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

    /**
     * Default constructor.
     */
    public ToDont() {}

    /**
     * Basic constructor.
     * 
     * @param owner User who created the ToDont
     * @param name Name of the ToDont
     * @param visibility Visibility of the ToDont
     */
    public ToDont(User owner, String name, Visibility visibility) {
        this();

        setOwner(owner);
        setModerators(new HashSet<User>());
        setName(name);
        setVisibility(visibility);
        setPledges(new HashSet<Pledge>());
        setComments(new HashSet<Comment>());
        setDatePublished(Timestamp.from(Instant.now()));
    }

    /**
     * Returns whether the given user has admin rights.
     * 
     * @param user User to test
     * @return True if the user is the owner of the ToDont
     */
    public boolean isAdmin(User user) {
        return user == owner;
    }

    /**
     * Returns whether the given user has moderation rights.
     * 
     * @param user User to test
     * @return True if the user is the owner or a moderator of the ToDont
     */
    public boolean isModerator(User user) {
        return isAdmin(user) || moderators.contains(user);
    }

    /**
     * Set the closed date to now.
     * 
     * @param user User attempting to close the ToDont. Must be the owner.
     * @return True if the ToDont was successfully closed
     */
    public boolean close(User user) {
        if (isAdmin(user)) {
            setDateClosed(Timestamp.from(Instant.now()));
            return true;
        }
        return false;
    }

    /**
     * Add a moderator to the ToDont.
     * 
     * @param user User that attempts to add a moderator. Must be the owner
     * @param moderator User that will gain moderator rights
     * @return True if the user was successfully added to the moderators
     */
    public boolean addModerator(User user, User moderator) {
        if (isAdmin(user)) {
            moderators.add(moderator);
            return true;
        }
        return false;
    }

    /**
     * Remove a moderator from the ToDont.
     * 
     * @param user User that attempts to remove a moderator. Must be the owner
     * @param moderator User that will lose moderator rights
     * @return True if the user was successfully removed from the moderators
     */
    public boolean removeModerator(User user, User moderator) {
        if (isAdmin(user)) {
            return moderators.remove(moderator);
        }
        return false;
    }

    /**
     * Whether the ToDont is already closed.
     * 
     * @return True if the ToDont is closed
     */
    public boolean isClosed() {
        if (dateClosed == null) {
            return false;
        }
        return !Timestamp.from(Instant.now()).before(dateClosed);
    }

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

    public void setDatePublished(Timestamp datePublished) {
        this.datePublished = datePublished;
    }

    public Timestamp getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Timestamp dateClosed) {
        this.dateClosed = dateClosed;
    }
}