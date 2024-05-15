package uk.ac.cf.cs.assessmenttracker.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {

    @GetMapping("/")
    public ModelAndView redirectToHome() {
        ModelAndView modelAndView = new ModelAndView("redirect:/module");
        return modelAndView;
    }

}
