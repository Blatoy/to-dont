package ch.hearc.todont.controllers;

import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.UserRepository;
import ch.hearc.todont.services.CommentService;
import ch.hearc.todont.services.ToDontService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToDontController {

    @Autowired
    private ToDontService toDontService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepo;

    /**
     * GET on a specific ToDont page.
     * 
     * @param toDontId UUID of the ToDont
     * @param userDetail User requesting the page
     * @param model Model passed down to the view
     * @return The page template name
     */
    @GetMapping("/{toDontId}")
    public String toDont(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetail,
        Model model) {

        User user = userRepo.findByName(userDetail.getUsername());
        model.addAttribute("user", user);

        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            model.addAttribute("toDont", toDont);
            return "todont";
        } 

        // TODO: use the "404" page once it exists
        return "error";
    }

    /**
     * POST on the /{id}/pledge, used to pledge to a ToDont.
     * 
     * @param toDontId UUID of the ToDont
     * @param userDetail User joining the ToDont
     * @return The page template name
     */
    @PostMapping("/{toDontId}/pledge")
    public String pledge(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetail,
        Model model
    ) {

        User user = userRepo.findByName(userDetail.getUsername());

        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            toDontService.pledgeToToDont(user, toDont);

            return "redirect:/" + toDont.getId();    
        }
        return "error";    
    }

    /**
     * POST on the /{id}/fail, used to fail a ToDont.
     * 
     * @param toDontId UUID of the ToDont
     * @param userDetail User failing the ToDont
     * @return The page template name
     */
    @PostMapping("/{toDontId}/fail")
    public String fail(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetail,
        Model model
    ) {

        User user = userRepo.findByName(userDetail.getUsername());

        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            toDontService.failToDont(user, toDont);
        }
        return "redirect:/" + toDont.getId();
    }

    /**
     * POST on /{id}/comment, used to post comments on a ToDont.
     * 
     * @param toDontId UUID of the ToDont
     * @param userDetail User posting the comment
     * @param comment Content of the comment
     * @return The page template name
     */
    @PostMapping("/{toDontId}/comment")
    public String comment(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetail,
        @RequestParam(name = "comment") String comment,
        Model model
    ) {

        User user = userRepo.findByName(userDetail.getUsername());

        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            commentService.post(user, toDont, comment);
            
            // TODO: error management
            // if (commentService.post(user, toDont, comment)) {
            //     // Successful comment
            // } else {
            //     // Failed comment
            // }

            return "redirect:/" + toDont.getId();
        }
        return "error";
    }

    /**
     * POST on /{id}/deleteComment, used to delete a comment on a ToDont.
     * 
     * @param toDontId UUID of the ToDont
     * @param userDetail User deleting the comment
     * @param commentId ID of the comment
     * @return The page template name
     */
    @PostMapping("/{toDontId}/deleteComment")
    public String deleteComment(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetail,
        @RequestParam(name = "commentId") long commentId,
        Model model
    ) {

        User user = userRepo.findByName(userDetail.getUsername());

        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            commentService.delete(user, commentId);
        }

        return "redirect:/" + toDont.getId();
    }
}