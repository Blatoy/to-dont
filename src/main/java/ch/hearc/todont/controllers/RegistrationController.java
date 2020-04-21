package ch.hearc.todont.controllers;

import ch.hearc.todont.datatransfer.UserDto;
import ch.hearc.todont.models.User;
import ch.hearc.todont.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// Based on https://www.baeldung.com/registration-with-spring-mvc-and-spring-security

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    
    /**
     * GET register.
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }
    
    /**
     * POST register.
     */
    @PostMapping("/register")
    public String register(
        @ModelAttribute("user") @Valid UserDto userDto,
        Model model
    ) {
        try {
            User registered = userService.registerUser(userDto);

            model.addAttribute("user", registered);
            return "redirect:home";
        } catch (Exception ex) {
            model.addAttribute("message", "This email is already used.");
            return "register";
        }   
    }
}