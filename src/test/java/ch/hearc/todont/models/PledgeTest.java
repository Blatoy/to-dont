package ch.hearc.todont.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ch.hearc.todont.models.ToDont.Visibility;

public class PledgeTest {

    @Test
    public void givenAPledge_whenCallFail_thenCallIsFailedReturnTrue() {
        User user = new User();
        ToDont toDont = new ToDont(user, "Test", Visibility.PUBLIC);
        Pledge pledge = new Pledge(user, toDont);

        pledge.fail();

        assertTrue(pledge.isFailed());
    }
}