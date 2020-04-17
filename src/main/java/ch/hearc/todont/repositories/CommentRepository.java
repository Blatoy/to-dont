package ch.hearc.todont.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.todont.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}