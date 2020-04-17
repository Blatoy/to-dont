package ch.hearc.todont.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 16, nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<ToDont> ownedToDonts;

    @ManyToMany(mappedBy = "moderators")
    private Set<ToDont> moderatedToDonts;

    @OneToMany(mappedBy = "user")
    private Set<Pledge> pledges;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    // METHODS

    /**
     * Pledges this user to the given ToDont
     * 
     * @param toDont ToDont the user will be pledged to
     * @return The new pledge object created in this method, or an existing pledge
     *         to the Todont if it exists
     */
    public Pledge pledgeTo(ToDont toDont) {
        Pledge pledge = getPledgeTo(toDont);

        if (pledge == null) {
            pledge = new Pledge();
            pledge.setUser(this);
            pledge.setToDont(toDont);
            pledge.setDateJoined(Timestamp.from(Instant.now()));
        }

        return pledge;
    }

    /**
     * Get the pledge made by this user to the given ToDont if it exists
     * 
     * @param toDont A ToDont the user should be pledged to
     * @return The Pledge object, if it exists, else null
     */
    public Pledge getPledgeTo(ToDont toDont) {
        for (Pledge pledge : pledges) {
            if (pledge.getToDont() == toDont) {
                return pledge;
            }
        }
        return null;
    }

    /**
     * To be called when the user fails the ToDont
     * 
     * @param pledge Pledge that has been failed
     */
    public void failPledge(Pledge pledge) {
        pledge.setDateFailed(Timestamp.from(Instant.now()));
    }

    /**
     * Posts a comment on behalf of this user on the given ToDont
     * 
     * @param toDont ToDont on which to post the comment
     * @param commentText Content of the comment
     * @return Comment object
     */
    public Comment commentOn(ToDont toDont, String commentText) {
        Pledge pledge = getPledgeTo(toDont);
        if (pledge == null) {
            // User is not pledged to this ToDont
            // He isn't allowed to comment on it
            return null;
        }

        Comment comment = new Comment();
        comment.setUser(this);
        comment.setToDont(toDont);
        comment.setContent(commentText);
        comment.setDate(Timestamp.from(Instant.now()));

        return comment;
    }

    // CONSTRUCTOR AND GETTERS-SETTERS

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ToDont> getOwnedToDonts() {
        return ownedToDonts;
    }

    public void setOwnedToDonts(Set<ToDont> ownedToDonts) {
        this.ownedToDonts = ownedToDonts;
    }

    public Set<ToDont> getModeratedToDonts() {
        return moderatedToDonts;
    }

    public void setModeratedToDonts(Set<ToDont> moderatedToDonts) {
        this.moderatedToDonts = moderatedToDonts;
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
}