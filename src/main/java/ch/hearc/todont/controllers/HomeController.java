package ch.hearc.todont.controllers;

import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepo;

    /**
     * GET on the home page.
     * 
     * @param userDetail User requesting the page
     * @param model Model passed down to the view
     * @param myToDontsPage Page number of the user's toDonts to display
     * @param publicToDontsPage Page number of the public toDonts to display
     * @return The page template name
     */
    @GetMapping(value = {"/", "/home"})
    public String home(
        @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetail,
        Model model,
        @RequestParam(defaultValue = "0") int myToDontsPage,
        @RequestParam(defaultValue = "0") int publicToDontsPage) {
        
        User user = userRepo.findByName(userDetail.getUsername());

        model.addAttribute("myToDonts", toDontService.getUserToDonts(
            user,
            myToDontsPage,
            MY_TODONTS_PER_PAGE
        ));
        model.addAttribute("publicToDonts", toDontService.getPublicToDonts(
            publicToDontsPage,
            PUBLIC_TODONTS_PER_PAGE
        ));
        model.addAttribute("user", user);
        return "home";
    }

    static final int MY_TODONTS_PER_PAGE = 5;
    static final int PUBLIC_TODONTS_PER_PAGE = 5;
}