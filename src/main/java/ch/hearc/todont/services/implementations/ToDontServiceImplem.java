package ch.hearc.todont.services.implementations;

import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.ToDont.Visibility;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.PledgeRepository;
import ch.hearc.todont.repositories.ToDontRepository;
import ch.hearc.todont.repositories.UserRepository;
import ch.hearc.todont.services.ToDontService;
import org.springframework.beans.factory.annotation.Autowired;

public class ToDontServiceImplem implements ToDontService {
    @Autowired
    PledgeRepository pledgeRepo;
    @Autowired
    ToDontRepository toDontRepo;
    @Autowired
    UserRepository userRepo;

    /**
     * Create and save a ToDont using the given parameters. The user will also
     * automatically be pledged to it.
     * 
     * @param owner      User creating the ToDont
     * @param name       Name of the ToDont
     * @param visibility Visibility of the ToDont
     * @return The newly created ToDont object
     */
    @Override
    public ToDont createToDont(User owner, String name, Visibility visibility) {
        ToDont toDont = new ToDont(owner, name, visibility);
        Pledge pledge = new Pledge(owner, toDont);

        toDontRepo.save(toDont);
        pledgeRepo.save(pledge);

        return toDont;
    }

    /**
     * Pledge a given user to the given ToDont. Especially useful for private
     * ToDonts.
     * 
     * @param owner    User attempting to invite to his ToDont
     * @param toDont   ToDont the given user will be pledged to
     * @param username Name of the user that will be pledged to the ToDont
     * @return The newly created Pledge object
     */
    @Override
    public Pledge inviteUserToToDont(User owner, ToDont toDont, String username) {
        if (toDont.isAdmin(owner)) {
            User invitee = userRepo.findByName(username);
            if (invitee != null) {
                Pledge pledge = new Pledge(invitee, toDont);
                pledgeRepo.save(pledge);
                return pledge;
            }
        }

        return null;
    }

    /**
     * Pledge the given user to the given ToDont.
     * 
     * @param user   User that will be pledged to the ToDont
     * @param toDont ToDont the user will be pledged to. Must not be private.
     */
    @Override
    public Pledge pledgeToToDont(User user, ToDont toDont) {
        if (pledgeRepo.findByUserAndToDont(user, toDont) != null) {
            // Pledge already exists
            return null;
        }
        if (toDont.getVisibility() != Visibility.PRIVATE) {
            Pledge pledge = new Pledge(user, toDont);
            pledgeRepo.save(pledge);
            return pledge;
        }
        return null;
    }

    /**
     * Add a moderator to the ToDont. The user attempting to do this must be admin,
     * and the new moderator should be pledged to the ToDont as well.
     * 
     * @param owner     User attempting to add a moderator
     * @param toDont    ToDont that will receive the new moderator
     * @param moderator User that will gain moderation rights
     */
    @Override
    public boolean addModerator(User owner, ToDont toDont, User moderator) {
        Pledge pledge = pledgeRepo.findByUserAndToDont(moderator, toDont);
        if (toDont.isAdmin(owner) && pledge != null) {
            toDont.addModerator(owner, moderator);
            toDontRepo.save(toDont);
            return true;
        }
        return false;
    }

    /**
     * Remove a moderator from the ToDont. The user attempting to do this must be
     * admin.
     * 
     * @param owner     User attempting to remove a moderator
     * @param toDont    ToDont that will lose a moderator
     * @param moderator User that will lose moderation rights
     */
    @Override
    public boolean removeModerator(User owner, ToDont toDont, User moderator) {
        if (toDont.removeModerator(owner, moderator)) {
            toDontRepo.save(toDont);
            return true;
        }
        return false;
    }

    /**
     * Close a ToDont. Meaning no one will be able to pledge to it anymore.
     * 
     * @param owner  User attempting to close the ToDont
     * @param toDont ToDont that will be closed
     */
    @Override
    public boolean close(User owner, ToDont toDont) {
        if (toDont.close(owner)) {
            toDontRepo.save(toDont);
            return true;
        }
        return false;
    }
}