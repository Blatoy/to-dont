package ch.hearc.todont.services;

import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.ToDont.Visibility;
import ch.hearc.todont.models.User;
import org.springframework.stereotype.Service;

@Service
public interface ToDontService {

    ToDont createToDont(User owner, String name, Visibility visibility);

    Pledge inviteUserToToDont(User owner, ToDont toDont, String username);

    Pledge pledgeToToDont(User user, ToDont toDont);

    boolean addModerator(User owner, ToDont toDont, User moderator);

    boolean addModerator(User owner, ToDont toDont, String moderator);

    boolean removeModerator(User owner, ToDont toDont, User moderator);

    boolean close(User owner, ToDont toDont);
}