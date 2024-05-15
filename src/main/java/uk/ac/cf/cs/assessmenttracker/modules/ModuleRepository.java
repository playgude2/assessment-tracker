package uk.ac.cf.cs.assessmenttracker.modules;

import java.util.List;

public interface ModuleRepository {
    List<Module> getModules(Integer defaultStartYear);

    List<Module> getUserModuleLeads(int userid, int defaultStartYear);
    List<Module> getUserModuleMods(int userid, int defaultStartYear);

    Module getModule(int moduleid);
    Module getModuleByAssessmentId(int assessmentid);
}
