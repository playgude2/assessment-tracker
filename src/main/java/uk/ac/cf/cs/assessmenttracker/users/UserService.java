package uk.ac.cf.cs.assessmenttracker.users;

import org.springframework.stereotype.Service;
import uk.ac.cf.cs.assessmenttracker.modules.Module;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository aUserRepository) {
        this.userRepository = aUserRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(int userid) { return userRepository.getUser(userid); }


}
