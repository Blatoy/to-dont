package ch.hearc.todont.services;

import ch.hearc.todont.models.Comment;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;

public interface CommentService {

    boolean post(User user, ToDont toDont, String content);

    boolean delete(User deleter, Comment comment);
}