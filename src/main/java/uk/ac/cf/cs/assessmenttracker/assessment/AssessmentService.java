package uk.ac.cf.cs.assessmenttracker.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.assessmenttracker.Config;
import uk.ac.cf.cs.assessmenttracker.modules.Module;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentService {

    private AssessmentRepository assessmentRepository;
    private ModuleRepository moduleRepository;

    @Autowired
    Config myConfig;

    public AssessmentService(AssessmentRepository aAssessmentRepository, ModuleRepository aModuleRepository) {
        this.assessmentRepository = aAssessmentRepository;
        this.moduleRepository = aModuleRepository;
    }

    public void saveModeratorReview(ModeratorReview moderatorReview) {
        assessmentRepository.saveModeratorReview(moderatorReview);
    }
    public void saveAssessmentReady(AssessmentReady assessmentReady) {
        assessmentRepository.saveAssessmentReady(assessmentReady);
    }

    public AssessmentAndModule getAssessmentAndModuleByAssessmentID(int assessmentID){
        Assessment assessment = assessmentRepository.getAssessment(assessmentID);
        AssessmentAndModule aNm = new AssessmentAndModule(assessment, moduleRepository.getModule(assessment.getModuleID()));
        return aNm;
    }

    public List<AssessmentAndModule> getAllModulesWithAssessments() {
        return getAllModulesWithAssessments(myConfig.getDefaultStartYear());
    }
    public List<AssessmentAndModule> getAllModulesWithAssessments(int startYear) {
        List<Module> modules = moduleRepository.getModules(startYear);
        List<AssessmentAndModule> aNmList = new ArrayList<AssessmentAndModule>();
        for (Module module : modules){
            List<Assessment> assessments = assessmentRepository.getAssessments(module.getId());
            if (assessments == null){
                assessments = new ArrayList<>();
            }
            aNmList.add(new AssessmentAndModule(assessments, module));
        }
        return aNmList;
    }
}
