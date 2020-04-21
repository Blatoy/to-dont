package ch.hearc.todont.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    /**
     * GET on the login page.
     * 
     * @param model Model passed down to the view
     * @return The page template name
     */
    @GetMapping("/login")
    public String home(Model model) {
        return "login";
    }
}