package uk.ac.cf.cs.assessmenttracker.assessment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssessmentRepositoryJDBC implements AssessmentRepository{
    private JdbcTemplate jdbc;
    private RowMapper<Assessment> assessmentMapper;

    public AssessmentRepositoryJDBC(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setAssessmentMapper();
    }

    private void setAssessmentMapper() {
        assessmentMapper = (rs, i) -> new Assessment(
                rs.getInt("id"),
                rs.getInt("moduleID"),
                rs.getInt("weighting"),
                rs.getString("assessmentName"),
                rs.getBoolean("assessmentReady"),
                rs.getDate("readyDate"),
                rs.getBoolean("intModerated"),
                rs.getDate("intModeratedDate"),
                rs.getString("intModComments")
        );
    }

    @Override
    public void saveAssessmentReady(AssessmentReady assessmentReady) {
        int id = assessmentReady.getId();
        String updateSql = "update  assessmentStatus set assessmentReady=?, readyDate = ? where id = ?";
        String insertSql = "insert into  assessmentStatus  (assessmentReady, readyDate, id) Values(?,?,?)";
        String checkSQL = "select count(*) from assessmentStatus where iD = ?";
        Integer results = jdbc.queryForObject(checkSQL, Integer.class, id);
        if (results.intValue() == 1) {
            jdbc.update(updateSql, assessmentReady.isAssessmentReady(),
                    assessmentReady.getReadyDate(),
                    assessmentReady.getId());
        } else {
            jdbc.update(insertSql, assessmentReady.isAssessmentReady(),
                    assessmentReady.getReadyDate(),
                    assessmentReady.getId());
        }
    }

    @Override
    public List<Assessment> getAssessments(int moduleID) {
        String sql = "select * from assessmentStatus where moduleID = ?";
        return jdbc.query(sql, assessmentMapper, moduleID);
    }

    @Override
    public Assessment getAssessment(int id) {
        String checkSQL = "select count(*) from assessmentStatus where id= ?";
        Integer results = jdbc.queryForObject(checkSQL, Integer.class, id);
        if (results.intValue() == 1){
            String sql = "select * from assessmentStatus where id = ?";
            return jdbc.queryForObject(sql, assessmentMapper, id);
        } else {
            return new Assessment(id);
        }
    }

    @Override
    public void saveModeratorReview(ModeratorReview moderatorReview) {
        String sql = "update assessmentStatus set " +
                "intModerated = ?, " +
                "intModeratedDate= ?, " +
                "intModComments = ? " +
                "where id = ?";
        jdbc.update(sql, moderatorReview.isIntModerated(),
                moderatorReview.getIntModeratedDate(),
                moderatorReview.getIntModComments(),
                moderatorReview.getId());
    }
}
