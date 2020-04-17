package ch.hearc.todont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/todont")
    public String todont() {
        return "todont";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/create")
    public String create() {
        return "create";
    }
}