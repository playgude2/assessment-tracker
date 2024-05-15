package uk.ac.cf.cs.assessmenttracker.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.cs.assessmenttracker.assessment.Assessment;
import uk.ac.cf.cs.assessmenttracker.users.User;

import java.util.List;

@Controller
public class ModuleController {
    private ModuleService moduleService;
    public ModuleController(ModuleService aModuleService) {
        this.moduleService = aModuleService;
    }

    @GetMapping("/module")
    public ModelAndView getModules() {
        ModelAndView modelAndView = new ModelAndView("module/moduleList");
        List<Module> modules = moduleService.getModules();
        modelAndView.addObject("modules", modules);
        return modelAndView;
    }

    @GetMapping("/module/start/{startYear}")
    public ModelAndView getModulesStartYear(@PathVariable("startYear") int startYear) {
        ModelAndView modelAndView = new ModelAndView("module/moduleList");
        List<Module> modules = moduleService.getModules(startYear);
        modelAndView.addObject("modules", modules);
        return modelAndView;
    }


    @GetMapping("/module/{moduleid}")
    public ModelAndView getModule(@PathVariable("moduleid") int moduleid) {
        ModelAndView modelAndView = new ModelAndView();
        Module module = moduleService.getModule(moduleid);
        List<Assessment> assessments = moduleService.getModuleAssessments(moduleid);
        if (module == null){
            modelAndView.setViewName("errors/resourceNotFound");
        } else {
            modelAndView.setViewName("module/moduleDetails");
            modelAndView.addObject("module", module);
            modelAndView.addObject("assessments", assessments);
        }
        return modelAndView;
    }


}
