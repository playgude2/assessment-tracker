package uk.ac.cf.cs.assessmenttracker.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.cs.assessmenttracker.assessment.AssessmentAndModule;
import uk.ac.cf.cs.assessmenttracker.modules.Module;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleService;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private ModuleService moduleService;
    public UserController(UserService aUserService, ModuleService aModuleService) {
        this.userService = aUserService;
        this.moduleService = aModuleService;
    }

    @GetMapping("/user")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("user/userList");
        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/user/{userid}")
    public ModelAndView getUser(@PathVariable("userid") int userid) {
        ModelAndView modelAndView = new ModelAndView("user/userDetails");
        User user = userService.getUser(userid);
        List<AssessmentAndModule> leadModulesaNmList = moduleService.getUsersModuleLeadsWithAssessments(userid);
        List<AssessmentAndModule> modModulesaNmList = moduleService.getUsersModuleModsWithAssessments(userid);
        modelAndView.addObject("user", user);
        modelAndView.addObject("leadModules", leadModulesaNmList);
        modelAndView.addObject("modModules", modModulesaNmList);
        return modelAndView;
    }

    @GetMapping("/user/{userid}/{startYear}")
    public ModelAndView getUser(@PathVariable("userid") int userid,
                                @PathVariable("startYear") int startYear) {
        ModelAndView modelAndView = new ModelAndView("user/userDetails");
        User user = userService.getUser(userid);
        List<AssessmentAndModule> leadModulesaNmList = moduleService.getUsersModuleLeadsWithAssessments(userid, startYear);
        List<AssessmentAndModule> modModulesaNmList = moduleService.getUsersModuleModsWithAssessments(userid, startYear);
        modelAndView.addObject("user", user);
        modelAndView.addObject("leadModules", leadModulesaNmList);
        modelAndView.addObject("modModules", modModulesaNmList);
        return modelAndView;
    }


}
