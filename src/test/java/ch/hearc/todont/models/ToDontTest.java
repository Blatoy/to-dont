package ch.hearc.todont.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.hearc.todont.models.ToDont.Visibility;
import org.junit.jupiter.api.Test;

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

    @Test
    public void givenAPledgedUser_whenCallIsPledged_thenReturnTrue() {
        User owner = new User();
        User user = new User();
        
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        toDont.getPledges().add(new Pledge(user, toDont));

        assertTrue(toDont.isPledged(user));
    }

    @Test
    public void givenANonPledgedUser_whenCallIsPledged_thenReturnTrue() {
        User owner = new User();
        User user = new User();
        
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);

        assertFalse(toDont.isPledged(user));
    }

    @Test
    public void givenAUserThatHasFailed_whenCallHasFailed_thenReturnTrue() {
        User owner = new User();
        User user = new User();
        
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(user, toDont);
        toDont.getPledges().add(pledge);

        pledge.fail();

        assertTrue(toDont.hasFailed(user));
    }

    @Test
    public void givenAUserThatHasNotFailed_whenCallHasFailed_thenReturnFalse() {
        User owner = new User();
        User user = new User();
        
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(user, toDont);
        toDont.getPledges().add(pledge);

        assertFalse(toDont.hasFailed(user));
    }

    @Test
    public void givenAToDontWith0Failures_whenCallGetNumberOfFailures_thenReturn0() {
        User owner = new User();
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);

        assertTrue(toDont.getNumberOfFailures() == 0);
    }

    private void generateFailures(ToDont toDont, int failures) {
        for (int i = 0; i < failures; i++) {
            User user = new User();
            
            Pledge pledge = new Pledge(user, toDont);
            toDont.getPledges().add(pledge);

            pledge.fail();
        }
    }

    @Test
    public void givenAToDontWith1Failure_whenCallGetNumberOfFailures_thenReturn1() {
        User owner = new User();
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);

        generateFailures(toDont, 1);

        assertTrue(toDont.getNumberOfFailures() == 1);
    }

    @Test
    public void givenAToDontWith2Failures_whenCallGetNumberOfFailures_thenReturn2() {
        User owner = new User();
        ToDont toDont = new ToDont(owner, "Test", Visibility.PUBLIC);

        generateFailures(toDont, 2);

        assertTrue(toDont.getNumberOfFailures() == 2);
    }
}