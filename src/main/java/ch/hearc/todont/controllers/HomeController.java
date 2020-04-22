package ch.hearc.todont.controllers;

import ch.hearc.todont.models.User;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.repositories.UserRepository;
import ch.hearc.todont.services.ToDontService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        @RequestParam(name="myToDontsPage", defaultValue = "1") int myToDontsPage,
        @RequestParam(name="publicToDontsPage", defaultValue = "1") int publicToDontsPage) {
        
        User user = userRepo.findByName(userDetail.getUsername());
        
        myToDontsPage -= 1;
        publicToDontsPage -= 1;

        Page<ToDont> myToDonts = toDontService.getUserToDonts(
            user,
            myToDontsPage,
            myToDontsPerPage
        );
        
        List<Integer> myTodontsPageNumbers = IntStream.rangeClosed(1, myToDonts.getTotalPages())
            .boxed()
            .collect(Collectors.toList());
        model.addAttribute("myToDontsPageNumbers", myTodontsPageNumbers);
        

        Page<ToDont> publicToDonts = toDontService.getPublicToDonts(
            publicToDontsPage,
            publicToDontsPerPage
        );

        List<Integer> publicToDontsPageNumbers = IntStream.rangeClosed(1, publicToDonts.getTotalPages())
            .boxed()
            .collect(Collectors.toList());
        model.addAttribute("publicToDontsPageNumbers", publicToDontsPageNumbers);

        model.addAttribute("myToDonts", myToDonts);
        model.addAttribute("publicToDonts", publicToDonts);
        return "home";
    }

    static final int myToDontsPerPage = 3;
    static final int publicToDontsPerPage = 3;
}