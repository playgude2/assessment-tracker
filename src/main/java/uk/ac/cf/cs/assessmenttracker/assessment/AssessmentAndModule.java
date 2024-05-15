package uk.ac.cf.cs.assessmenttracker.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.ac.cf.cs.assessmenttracker.modules.Module;

import java.util.List;

@Data
@AllArgsConstructor
public class AssessmentAndModule {
    List<Assessment> assessments;
    Module module;

    public AssessmentAndModule(Assessment aAssessment) {
        this.assessments = List.of(aAssessment);
    }

    public AssessmentAndModule(Assessment aAssessment, Module aModule) {
        assessments = List.of(aAssessment);
        module = aModule;
    }
}
