package uk.ac.cf.cs.assessmenttracker.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class AssessmentReady {
    private int id;
    private boolean assessmentReady;
    private Date readyDate;
}
