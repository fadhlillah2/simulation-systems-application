package net.javaguides.springboot.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.service.impl.UserServiceImpl;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserRegistrationControllerTest {

    @Mock
    private UserServiceImpl userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserRegistrationController userRegistrationController = new UserRegistrationController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController)
                .setViewResolvers((s, locale) -> new InternalResourceView(s)) // This line added
                .build();
    }

    @Test
    public void testUserRegistrationDtoModelAttribute() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", instanceOf(UserRegistrationDto.class)));

        Mockito.verifyNoInteractions(userService);
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void testRegisterUserAccount() throws Exception {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName("Test First Name");
        userRegistrationDto.setLastName("Test Last Name");
        userRegistrationDto.setEmail("test@example.com");
        userRegistrationDto.setPassword("pass");

        // Mock the userService
        UserService userService = Mockito.mock(UserService.class);

        // Create an instance of the controller and set the mocked service
        UserRegistrationController controller = new UserRegistrationController(userService);

        // Define the behavior of the mocked service
        User user = new User(); // Assuming you have a User class
        Mockito.when(userService.save(Mockito.any(UserRegistrationDto.class))).thenReturn(user);

        // Perform the test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userRegistrationDto)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/registration?success"));

        // Verify that the userService.save() method was invoked
        Mockito.verify(userService, Mockito.times(1)).save(Mockito.any(UserRegistrationDto.class));
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }





}
