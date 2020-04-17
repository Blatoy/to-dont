package ch.hearc.todont.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(
        name = "toDontId",
        nullable = false
    )
    private ToDont toDont;

    @ManyToOne
    @JoinColumn(
        name = "userId",
        nullable = false
    )
    private User user;

    @Column(
        name = "date",
        nullable = false
    )
    private Timestamp date;

    @Column(
        name = "content",
        nullable = false
    )
    private String content;

    // CONSTRUCTOR AND GETTERS-SETTERS

    public Comment() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToDont getToDont() {
        return toDont;
    }

    public void setToDont(ToDont toDont) {
        this.toDont = toDont;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}