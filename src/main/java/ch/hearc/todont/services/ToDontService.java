package ch.hearc.todont.services;

import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.ToDont.Visibility;
import ch.hearc.todont.models.User;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ToDontService {

    ToDont createToDont(User owner, String name, Visibility visibility);

    ToDont getToDont(User user, UUID toDontId);

    Pledge inviteUserToToDont(User owner, ToDont toDont, String username);

    Pledge pledgeToToDont(User user, ToDont toDont);

    boolean failToDont(User user, ToDont toDont);

    boolean addModerator(User owner, ToDont toDont, User moderator);

    boolean addModerator(User owner, ToDont toDont, String moderator);

    boolean removeModerator(User owner, ToDont toDont, User moderator);

    boolean close(User owner, ToDont toDont);

    Page<ToDont> getUserToDonts(User user, int page, int numberOfElements);

    Page<ToDont> getPublicToDonts(String title, String author, int page, int numberOfElements);
}