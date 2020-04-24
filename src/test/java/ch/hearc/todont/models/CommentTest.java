package ch.hearc.todont.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.hearc.todont.models.ToDont.Visibility;
import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void givenACommentAndItsOwner_whenCallCanDelete_thenReturnTrue() {
        User owner = new User();
        User commenter = new User();

        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(commenter, toDont);

        Comment comment = new Comment(pledge, "Hello");

        assertTrue(comment.canDelete(commenter));
    }
    
    @Test
    public void givenACommenterAndAModerator_whenCallCanDelete_thenReturnTrue() {
        User owner = new User();
        User moderator = new User();
        User commenter = new User();

        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        
        Pledge commenterPledge = new Pledge(commenter, toDont);
        
        new Pledge(moderator, toDont);
        toDont.addModerator(owner, moderator);

        Comment comment = new Comment(commenterPledge, "Hello");

        assertTrue(comment.canDelete(moderator));
    }

    @Test
    public void givenACommenterAndANonModerator_whenCallCanDelete_thenReturnFalse() {
        User owner = new User();
        User anyone = new User();
        User commenter = new User();

        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        
        Pledge commenterPledge = new Pledge(commenter, toDont);
        
        new Pledge(anyone, toDont);

        Comment comment = new Comment(commenterPledge, "Hello");

        assertFalse(comment.canDelete(anyone));
    }
}