package ch.hearc.todont.services.implementations;

import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.ToDont.Visibility;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.PledgeRepository;
import ch.hearc.todont.repositories.ToDontRepository;
import ch.hearc.todont.repositories.UserRepository;
import ch.hearc.todont.services.ToDontService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
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
        toDontRepo.save(toDont);
        
        Pledge pledge = new Pledge(owner, toDont);
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
     * Add a moderator to the ToDont. The user attempting to do this must be admin,
     * and the new moderator should be pledged to the ToDont as well.
     * 
     * @param owner     User attempting to add a moderator
     * @param toDont    ToDont that will receive the new moderator
     * @param username  Name of the user that will gain moderation rights
     */
    @Override
    public boolean addModerator(User owner, ToDont toDont, String username) {
        

        if (toDont.isAdmin(owner)) {
            User moderator = userRepo.findByName(username);
            if (moderator != null) {
                toDont.addModerator(owner, moderator);
                toDontRepo.save(toDont);
                return true;
            }
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

    @Override
    public Page<ToDont> getUserToDonts(User user, int page, int numberOfElements) {
        Page<Pledge> pledges = pledgeRepo.findByUser(user, PageRequest.of(page, numberOfElements));
        return pledges.map(pledge -> pledge.getToDont());
    }

    @Override
    public Page<ToDont> getPublicToDonts(
        String title,
        String author, 
        int page,
        int numberOfElements
    ) {
        if (author.length() > 0) {
            User owner = userRepo.findByName(author);
            if (owner != null) {
                return toDontRepo.findPublicToDonts(
                    title,
                    owner,
                    PageRequest.of(page, numberOfElements)
                );
            } else {
                return Page.empty();
            }
        } else {
            return toDontRepo.findPublicToDonts(
                title,
                PageRequest.of(page, numberOfElements)
            );
        }
    }

    @Override
    public ToDont getToDont(User user, UUID toDontId) {
        Optional<ToDont> optToDont = toDontRepo.findById(toDontId);
        if (optToDont.isPresent()) {
            ToDont toDont = optToDont.get();
            boolean hasAccess = false;
            if (toDont.getVisibility() == Visibility.PRIVATE) {
                // We have to check if the user can see the ToDont
                Pledge pledge = pledgeRepo.findByUserAndToDont(user, toDont);
                if (pledge != null) {
                    hasAccess = true;
                }
            } else {
                hasAccess = true;
            }

            if (hasAccess) {
                return toDont;
            }
        }
        return null;
    }

    @Override
    public boolean failToDont(User user, ToDont toDont) {
        Pledge pledge = pledgeRepo.findByUserAndToDont(user, toDont);
        if (pledge != null && !pledge.isFailed()) {
            pledge.fail();
            pledgeRepo.save(pledge);
            return true;
        }
        
        return false;
    }
}