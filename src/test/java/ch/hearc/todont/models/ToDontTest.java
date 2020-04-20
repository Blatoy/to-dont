package ch.hearc.todont.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ch.hearc.todont.models.ToDont.Visibility;

public class ToDontTest {

    @Test
    public void givenAToDont_whenCallIsAdminWithOwner_thenReturnTrue() {
        User user = new User();
        ToDont toDont = new ToDont(user, "Test", Visibility.PUBLIC);

        assertTrue(toDont.isAdmin(user));
    }

    @Test
    public void givenAToDont_whenCallIsAdminWithoutOwner_thenReturnFalse() {
        User user = new User();
        User anyone = new User();
        ToDont toDont = new ToDont(user, "Test", Visibility.PUBLIC);

        assertFalse(toDont.isAdmin(anyone));
    }

    @Test
    public void givenAToDont_whenCallIsModeratorWithOwner_thenReturnTrue() {
        User user = new User();
        ToDont toDont = new ToDont(user, "Test", Visibility.PUBLIC);

        assertTrue(toDont.isModerator(user));
    }

    @Test
    public void givenAToDont_whenCallIsModeratorWithoutModerator_thenReturnFalse() {
        User owner = new User();
        User anyone = new User();
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);

        assertFalse(toDont.isModerator(anyone));
    }

    @Test
    public void givenAToDont_whenCallIsModeratorWithModerator_thenReturnTrue() {
        User owner = new User();
        User moderator = new User();
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        
        toDont.addModerator(owner, moderator);

        assertTrue(toDont.isModerator(moderator));
    }

    @Test
    public void givenAClosedToDont_whenCallIsClosed_thenReturnTrue() {
        User user = new User();
        ToDont toDont = new ToDont(user, "Test", Visibility.PUBLIC);

        toDont.close(user);

        assertTrue(toDont.isClosed());
    }
}