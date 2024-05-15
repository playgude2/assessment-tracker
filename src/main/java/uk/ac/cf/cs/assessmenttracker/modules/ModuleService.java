package uk.ac.cf.cs.assessmenttracker.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.assessmenttracker.Config;
import uk.ac.cf.cs.assessmenttracker.assessment.Assessment;
import uk.ac.cf.cs.assessmenttracker.assessment.AssessmentAndModule;
import uk.ac.cf.cs.assessmenttracker.assessment.AssessmentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleService {
    private ModuleRepository moduleRepository;
    private AssessmentRepository assessmentRepository;

    @Autowired
    Config myConfig;

    public ModuleService(ModuleRepository aMenuRepository, AssessmentRepository aAssessmentRepository) {
        this.moduleRepository = aMenuRepository;
        this.assessmentRepository = aAssessmentRepository;
    }

    public List<Module> getModules() { return moduleRepository.getModules(myConfig.getDefaultStartYear()); }
    public List<Module> getModules(int startYear) { return moduleRepository.getModules(startYear); }
    public List<Module> getUsersModuleLeads(int userid) { return moduleRepository.getUserModuleLeads(userid, myConfig.getDefaultStartYear()); }
    public List<Module> getUsersModuleMods(int userid) { return moduleRepository.getUserModuleMods(userid, myConfig.getDefaultStartYear()); }
    public Module getModule(int moduleid) {
        return moduleRepository.getModule(moduleid);
    }

    public Assessment getModuleAssessment(int moduleid) { return assessmentRepository.getAssessment(moduleid);   }

    public List<Assessment> getModuleAssessments(int moduleid) { return assessmentRepository.getAssessments(moduleid); }

    public List<AssessmentAndModule> getUsersModuleLeadsWithAssessments(int userid) {
        return getUsersModuleLeadsWithAssessments(userid, myConfig.getDefaultStartYear());
    }
    public List<AssessmentAndModule> getUsersModuleLeadsWithAssessments(int userid, int startYear) {
        List<Module> modules = moduleRepository.getUserModuleLeads(userid, startYear);
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

    public List<AssessmentAndModule> getUsersModuleModsWithAssessments(int userid) {
        return getUsersModuleModsWithAssessments(userid, myConfig.getDefaultStartYear());
    }
    public List<AssessmentAndModule> getUsersModuleModsWithAssessments(int userid, int startYear) {
        List<Module> modules = moduleRepository.getUserModuleMods(userid, startYear);
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

    public Module getModuleByAssessmentID(int assessmentid) {
        return moduleRepository.getModuleByAssessmentId(assessmentid);
    }
}
