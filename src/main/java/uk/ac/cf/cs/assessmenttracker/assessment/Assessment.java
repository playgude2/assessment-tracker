package uk.ac.cf.cs.assessmenttracker.assessment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Assessment {
    private int id;
    private int moduleID;
    private int weighting;
    private String assessmentName;
    private boolean assessmentReady;
    @NotEmpty(message = "The ready date cannot be empty please use the original date of first moderation")
    private Date readyDate;
    private boolean intModerated;
    private Date intModeratedDate;
    private String intModComments;

    public Assessment(){}
    public Assessment(int aModuleID){
        this.moduleID = aModuleID;
    }
    public Assessment(int aId, String aAssessmentName){
        this.id = aId;
        this.assessmentName = aAssessmentName;
    }
}

