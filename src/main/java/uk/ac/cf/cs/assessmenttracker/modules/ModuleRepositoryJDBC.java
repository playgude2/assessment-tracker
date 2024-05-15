package uk.ac.cf.cs.assessmenttracker.modules;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModuleRepositoryJDBC implements ModuleRepository {
    private JdbcTemplate jdbc;
    private RowMapper<Module> moduleMapper;

    public ModuleRepositoryJDBC(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setModuleMapper();
    }

    private void setModuleMapper() {

        moduleMapper = (rs, i) -> new Module(
                rs.getInt("id"),
                rs.getString("moduleName"),
                rs.getString("moduleNumber"),
                rs.getInt("startYear")
        );
    }


    @Override
    public List<Module> getModules(Integer defaultStartYear) {
        String sql = "select * from modules where startYear in (?, ?)";
        return jdbc.query(sql, moduleMapper, defaultStartYear, defaultStartYear+1);
    }

    @Override
    public List<Module> getUserModuleLeads(int userid, int defaultStartYear) {
        String sql = "select * from modules " +
                "where id in (select moduleID from moduleLeaders where userID = ? ) " +
                "and startYear in (?, ?)  ";
        return jdbc.query(sql, moduleMapper, userid, defaultStartYear, defaultStartYear+1);
    }

    @Override
    public List<Module> getUserModuleMods(int userid, int defaultStartYear) {
        String sql = "select * from modules " +
                "where id in (select moduleID from moduleModerators where userID = ? ) " +
                "and startYear in (?, ?) ";
        return jdbc.query(sql, moduleMapper, userid, defaultStartYear, defaultStartYear+1);
    }

    @Override
    public Module getModule(int moduleid) {
        String sql = "select * from modules where id = ?";
        List<Module> modules = jdbc.query(sql, moduleMapper, moduleid);
        Module module =  (modules.size() ==1) ? modules.get(0) : null;
        return module;

    }

    @Override
    public Module getModuleByAssessmentId(int assessmentid) {
        String sql = "select * from modules where id in (select moduleId from assessmentStatus where id=?)";
        List<Module> modules = jdbc.query(sql, moduleMapper, assessmentid);
        Module module =  (modules.size() ==1) ? modules.get(0) : null;
        return module;
    }
}
