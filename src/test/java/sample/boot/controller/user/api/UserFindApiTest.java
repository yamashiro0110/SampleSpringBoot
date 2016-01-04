package sample.boot.controller.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes = Main.class)
@SpringApplicationConfiguration(classes = {JacksonAutoConfiguration.class})
public class UserFindApiTest {
    private MockMvc mockMvc;
    @Resource
    private ObjectMapper objectMapper;
    @InjectMocks
    private UserFindApi userFindApi;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userFindApi).build();
    }

    @Test
    public void testUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/find/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}