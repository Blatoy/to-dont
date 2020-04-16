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

}