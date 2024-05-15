package uk.ac.cf.cs.assessmenttracker.modules;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Module {

    private int id;
    @NotEmpty(message = "The moduleName cannot be empty")
    private String moduleName;
    @NotEmpty(message = "The moduleNumber cannot be empty")
    private String moduleNumber;
    @NotEmpty(message = "The moduleNumber cannot be empty")
    private int startYear;
//
//    public Module(){
//        this.moduleName = "";
//        this.moduleNumber = "";
//    }

}
