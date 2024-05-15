package uk.ac.cf.cs.assessmenttracker.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uk.ac.cf.cs.assessmenttracker.modules.Module;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleRepository;

import java.util.List;

@Repository
public class UserRepositoryJDBC implements UserRepository {
    private JdbcTemplate jdbc;
    private RowMapper<User> userMapper;

    public UserRepositoryJDBC(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setUserMapper();
    }

    private void setUserMapper() {

        userMapper = (rs, i) -> new User(
                rs.getInt("id"),
                rs.getString("emailAddress"),
                rs.getString("firstName"),
                rs.getString("lastName")
        );
    }


    @Override
    public List<User> getUsers() {
        String sql = "select * from users ";
        return jdbc.query(sql, userMapper);

    }

    @Override
    public User getUser(int userid) {
        String sql = "select * from users where id = ?";
        User user = jdbc.queryForObject(sql, userMapper, userid);
        return user;
    }


}
