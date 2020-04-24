package ch.hearc.todont.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.hearc.todont.models.ToDont.Visibility;
import org.junit.jupiter.api.Test;


public class PledgeTest {

    @Test
    public void givenAPledge_whenCallFail_thenCallIsFailedReturnTrue() {
        User user = new User();
        ToDont toDont = new ToDont(user, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(user, toDont);

        pledge.fail();

        assertTrue(pledge.isFailed());
    }

    @Test
    public void givenAModeratorPledge_whenCallisModerator_thenReturnTrue() {
        User owner = new User();
        User mod = new User();

        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(mod, toDont);

        toDont.addModerator(owner, mod);

        assertTrue(pledge.isModerator());
    }

    @Test
    public void givenANonModeratorPledge_whenCallisModerator_thenReturnFalse() {
        User owner = new User();
        User mod = new User();

        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(mod, toDont);

        assertFalse(pledge.isModerator());
    }
}