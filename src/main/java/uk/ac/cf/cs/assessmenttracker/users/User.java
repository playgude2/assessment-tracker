package uk.ac.cf.cs.assessmenttracker.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class User {
    private int id;
    @Email(message = "check email syntax")
    private String emailAddress;
    @NotEmpty(message = "The firstName cannot be empty")
    private String firstName;
    @NotEmpty(message = "The lastName cannot be empty")
    private String lastName;
}
