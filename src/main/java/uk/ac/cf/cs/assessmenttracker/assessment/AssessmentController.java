package uk.ac.cf.cs.assessmenttracker.assessment;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleService;
import uk.ac.cf.cs.assessmenttracker.modules.Module;

import java.util.List;

@Controller
public class AssessmentController {

    private ModuleService moduleService;
    private AssessmentService assessmentService;
    public AssessmentController(ModuleService aModuleService, AssessmentService aAssessmentService) {
        this.moduleService = aModuleService;
        this.assessmentService = aAssessmentService;
    }

    @GetMapping("/assessment")
    public ModelAndView getModulesAndAllAssociatedAssessments() {
        ModelAndView modelAndView = new ModelAndView("assessments/moduleAssessmentList");
        List<AssessmentAndModule> aNmList = assessmentService.getAllModulesWithAssessments();
        modelAndView.addObject("modules", aNmList);
        return modelAndView;
    }

    @GetMapping("/assessment/start/{startYear}")
    public ModelAndView getModulesAndAllAssociatedAssessmentsStartYear(@PathVariable("startYear") int startYear) {
        ModelAndView modelAndView = new ModelAndView("assessments/moduleAssessmentList");
        List<AssessmentAndModule> aNmList = assessmentService.getAllModulesWithAssessments(startYear);
        modelAndView.addObject("modules", aNmList);
        return modelAndView;
    }

    @GetMapping("/assessment/{assessmentid}")
    public ModelAndView getModule(@PathVariable("assessmentid") int assessmentid) {
        ModelAndView modelAndView = new ModelAndView();
        AssessmentAndModule aNm = assessmentService.getAssessmentAndModuleByAssessmentID(assessmentid);
        if (aNm.module == null){
            modelAndView.setViewName("errors/resourceNotFound");
        } else {
            modelAndView.setViewName("assessments/assessmentDetails");
            modelAndView.addObject("module", aNm.module);
            modelAndView.addObject("assessment", aNm.assessments.get(0));
        }
        return modelAndView;
    }

    //This works because the assessment ID is currently the module ID - if this changes there will be a problem.
    @GetMapping("/assessment/ready/{assessmentid}")
    public ModelAndView getReadyForm(@PathVariable("assessmentid") int assessmentid) {
        ModelAndView modelAndView = new ModelAndView("assessments/ready");
        Assessment assessment = moduleService.getModuleAssessment(assessmentid); // maybe change to AssessmentService method later?
        Module module = moduleService.getModule(assessmentid);
        modelAndView.addObject("assessment", assessment);
        modelAndView.addObject("module", module);
        return modelAndView;
    }

    //This works because the assessment ID is currently the module ID - if this changes there will be a problem.
    @GetMapping("/assessment/moderatorReview/{assessmentid}")
    public ModelAndView getIntReviewForm(@PathVariable("assessmentid") int assessmentid) {
        ModelAndView modelAndView = new ModelAndView("assessments/moderatorReview");
        AssessmentAndModule aNm = assessmentService.getAssessmentAndModuleByAssessmentID(assessmentid);
        modelAndView.addObject("assessment", aNm.getAssessments().get(0));
        modelAndView.addObject("module", aNm.getModule() );
        System.out.println(aNm.getModule()+"  "+ aNm.getAssessments().get(0).getModuleID());
        return modelAndView;
    }

    @PostMapping("/assessment/moderatorReview/{assessmentid}")
    public ModelAndView submitForm(@Valid @ModelAttribute("moderatorReview") ModeratorReview moderatorReview,
                                   BindingResult bindingResult, Model model,
                                   @PathVariable("assessmentid") int assessmentid){
        if (bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView("assessments/moderatorReview", model.asMap());
            AssessmentAndModule aNm = assessmentService.getAssessmentAndModuleByAssessmentID(assessmentid);
            modelAndView.addObject("assessment", aNm.getAssessments().get(0));
            modelAndView.addObject("module", aNm.getModule() );
            System.out.println("Errors"+ assessmentid );
            return modelAndView;
        }
        System.out.println(moderatorReview);
        assessmentService.saveModeratorReview(moderatorReview);
        ModelAndView modelAndView = new ModelAndView("redirect:/assessment/"+moderatorReview.getId());
        return modelAndView;
    }

    @PostMapping("/assessment/ready/{assessmentid}")
    public ModelAndView submitForm(@Valid @ModelAttribute("review") AssessmentReady assessmentReady,
                                   BindingResult bindingResult, Model model,
                                    @PathVariable("assessmentid") int assessmentid){
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("assessments/ready", model.asMap());
            AssessmentAndModule aNm = assessmentService.getAssessmentAndModuleByAssessmentID(assessmentid);
            modelAndView.addObject("assessment", aNm.getAssessments().get(0));
            modelAndView.addObject("module", aNm.getModule() );
            System.out.println("Errors");
            return modelAndView;
        }
        System.out.println(assessmentReady);
        assessmentService.saveAssessmentReady(assessmentReady);
        ModelAndView modelAndView = new ModelAndView("redirect:/assessment/"+assessmentReady.getId());
        return modelAndView;
    }
}
