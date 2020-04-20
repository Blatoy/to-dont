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
        } else {
            // TODO: use the "404" page once it exists
            return "error";
        }
    }
}