package uk.ac.cf.cs.assessmenttracker.assessment;

import org.springframework.stereotype.Repository;
import uk.ac.cf.cs.assessmenttracker.modules.Module;

import java.util.List;
public interface AssessmentRepository {
    Assessment getAssessment(int moduleid);
    List<Assessment> getAssessments(int moduleid);

    void saveAssessmentReady(AssessmentReady assessmentReady);
    void saveModeratorReview(ModeratorReview moderatorReview);


}
