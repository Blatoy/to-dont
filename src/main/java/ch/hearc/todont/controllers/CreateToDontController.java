package ch.hearc.todont.controllers;

import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;
import ch.hearc.todont.repositories.ToDontRepository;
import ch.hearc.todont.services.ToDontService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todont/create/")
public class CreateToDontController {

    @Autowired
    private ToDontService toDontService;

    @Autowired
    private ToDontRepository toDontRepo;
    
    /**
     * GET on the "/todont/create/" page.
     * @param model Model passed down to the view
     * @return      The page template name
     */
    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("toDont", new ToDont());
        model.addAttribute("moderators", new ArrayList<String>());
        model.addAttribute("pledgers", new ArrayList<String>());
        return "create";
    }

    /**
     * Post to add new to-dont.
     * @param user              User adding the to-dont
     * @param newToDont         New to-dont to be added
     * @param moderatorsList    List of the moderators for the newly created to-dont
     * @param pledgersList      List of pledgers for the newly created to-dont
     * @return                  The page template name       
     */
    @PostMapping("/")
    public String createToDont(
        @AuthenticationPrincipal User user, 
        @ModelAttribute ToDont newToDont,
        @ModelAttribute ArrayList<String> moderatorsList,
        @ModelAttribute ArrayList<String> pledgersList) {
        for (String moderatorName : moderatorsList) {    
            toDontService.addModerator(user, newToDont, moderatorName);
        }

        for (String pledgerName : pledgersList) {
            toDontService.inviteUserToToDont(user, newToDont, pledgerName);
        }

        if (toDontRepo.save(newToDont) != null) {
            return "home";
        } else {
            // Error, stay on page
            return "false";
        }
        
    }
    
}