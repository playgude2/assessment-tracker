package uk.ac.cf.cs.assessmenttracker.user;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.cs.assessmenttracker.assessment.Assessment;
import uk.ac.cf.cs.assessmenttracker.assessment.AssessmentAndModule;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleRepository;
import uk.ac.cf.cs.assessmenttracker.modules.ModuleService;
import uk.ac.cf.cs.assessmenttracker.users.User;
import uk.ac.cf.cs.assessmenttracker.modules.Module;
import uk.ac.cf.cs.assessmenttracker.users.UserController;
import uk.ac.cf.cs.assessmenttracker.users.UserRepository;
import uk.ac.cf.cs.assessmenttracker.users.UserService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class LightweightUserTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private ModuleService moduleService;


    @Test
    public void getUserTest() throws Exception {

        User user = new User(1, "test@test.co.uk", "TestFirst", "TestLast");
        Module moduleLeads = new Module(1, "Web Apps", "CMT612", 2023);
        List<Assessment> assessments = Arrays.asList(new Assessment( 1,"only Assessment"));
        AssessmentAndModule assessmentAndModule = new AssessmentAndModule( assessments, moduleLeads);

        // This will return what the Repo would have returned
        given(this.userService.getUser(1)).willReturn(user);
        given(this.moduleService.getUsersModuleLeads(1)).willReturn(Arrays.asList(moduleLeads));
        given(this.moduleService.getUsersModuleLeadsWithAssessments(1)).willReturn(Arrays.asList(assessmentAndModule));


        this.mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("CMT612")))
                .andExpect(content().string(containsString("only Assessment")));
    }

}
