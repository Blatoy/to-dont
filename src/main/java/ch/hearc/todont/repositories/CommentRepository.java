package ch.hearc.todont.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.todont.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}