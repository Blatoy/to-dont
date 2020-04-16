package ch.hearc.todont.models;

import java.util.Set;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<ToDont> ownedToDonts;
    
    @ManyToMany(mappedBy = "moderators")
    private Set<ToDont> moderatedToDonts;

    @ManyToMany(mappedBy = "participants")
    private Set<ToDont> toDonts;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    public User() {}

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

    public Set<ToDont> getToDonts() {
        return toDonts;
    }

    public void setToDonts(Set<ToDont> toDonts) {
        this.toDonts = toDonts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}