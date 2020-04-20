package ch.hearc.todont.repositories;

import ch.hearc.todont.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}