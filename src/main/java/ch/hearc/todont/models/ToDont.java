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
}