package uk.ac.cf.cs.assessmenttracker.assessment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class ModeratorReview {
    private int id;
    private boolean intModerated;
    @NotNull(message = "The date cannot be empty please use first date moderated")
    private Date intModeratedDate;
    private String intModComments;
}
