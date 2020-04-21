package ch.hearc.todont.controllers;

import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;
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

    /**
     * GET on a specific ToDont page.
     * 
     * @param toDontId UUID of the ToDont
     * @param user User requesting the page
     * @param model Model passed down to the view
     * @return The page template name
     */
    @GetMapping("/{toDontId}")
    public String toDont(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal User user, Model model) {

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
     * @param user User joining the ToDont
     * @return The page template name
     */
    @PostMapping("/{toDontId}/pledge")
    public String comment(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal User user,
        Model model
    ) {
        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            toDontService.pledgeToToDont(user, toDont);

            model.addAttribute("toDont", toDont);
            return "todont";
        }
        return "error";
    }

    /**
     * POST on the /{id}/comment, used to post comments on a ToDont.
     * 
     * @param toDontId UUID of the ToDont
     * @param user User posting the comment
     * @param comment Content of the comment
     * @return The page template name
     */
    @PostMapping("/{toDontId}/comment")
    public String comment(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal User user,
        @RequestParam(name = "comment") String comment,
        Model model
    ) {
        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            if (commentService.post(user, toDont, comment)) {
                // Successful comment
            } else {
                // Failed comment
            }

            model.addAttribute("toDont", toDont);
            return "todont";
        }
        return "error";
    }

    /**
     * POST on the /{id}/fail, used to fail a ToDont.
     * 
     * @param toDontId UUID of the ToDont
     * @param user User failing the ToDont
     * @return The page template name
     */
    @PostMapping("/{toDontId}/fail")
    public String pledge(
        @PathVariable("toDontId") UUID toDontId,
        @AuthenticationPrincipal User user,
        Model model
    ) {
        ToDont toDont = toDontService.getToDont(user, toDontId);
        if (toDont != null) {
            toDontService.failToDont(user, toDont)
        }
        model.addAttribute("toDont", toDont);
        return "todont";
    }
}