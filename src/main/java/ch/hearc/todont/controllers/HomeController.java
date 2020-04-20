package ch.hearc.todont.controllers;

import ch.hearc.todont.models.User;
import ch.hearc.todont.services.ToDontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ToDontService toDontService;

    /**
     * GET on the home page.
     * 
     * @param user User requesting the page
     * @param model Model passed down to the view
     * @param myToDontsPage Page number of the user's toDonts to display
     * @param publicToDontsPage Pagge number of the public toDonts to display
     * @return The page template name
     */
    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model,
        @RequestParam(defaultValue = "0") int myToDontsPage,
        @RequestParam(defaultValue = "0") int publicToDontsPage) {
        model.addAttribute("myToDonts", toDontService.getUserToDonts(
            user,
            myToDontsPage,
            myToDontsPerPage
        ));
        model.addAttribute("publicToDonts", toDontService.getPublicToDonts(
            publicToDontsPage,
            publicToDontsPerPage
        ));
        return "home";
    }

    static final int myToDontsPerPage = 5;
    static final int publicToDontsPerPage = 5;
}