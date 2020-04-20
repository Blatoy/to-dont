package ch.hearc.todont.services.implementations;

import ch.hearc.todont.models.Comment;
import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.CommentRepository;
import ch.hearc.todont.repositories.PledgeRepository;
import ch.hearc.todont.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImplem implements CommentService {

    @Autowired
    PledgeRepository pledgeRepo;
    @Autowired
    CommentRepository commentRepo;

    /**
     * Post a comment from the given user, on the given ToDont. The user must be
     * pledged to the ToDont in order to post a comment.
     * 
     * @param user    Author of the comment. Must be pledged to the ToDont
     * @param toDont  ToDont on which to post the comment
     * @param content Text of the new comment
     */
    @Override
    public boolean post(User user, ToDont toDont, String content) {
        Pledge pledge = pledgeRepo.findByUserAndToDont(user, toDont);
        if (pledge != null) {
            Comment comment = new Comment(pledge, content);
            commentRepo.save(comment);
            return true;
        }
        return false;
    }

    /**
     * Delete a comment.
     * 
     * @param deleter User who attempts to delete the comment. Must be either the
     *                author of the comment, or have mod rights on the ToDont
     * @param comment Comment to delete
     */
    @Override
    public boolean delete(User deleter, Comment comment) {
        if (comment.canDelete(deleter)) {
            commentRepo.delete(comment);
            return true;
        }
        return false;
    }

}