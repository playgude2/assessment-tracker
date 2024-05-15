package uk.ac.cf.cs.assessmenttracker.users;

import uk.ac.cf.cs.assessmenttracker.modules.Module;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();

    User getUser(int userid);

}
