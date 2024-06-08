package Service;

import com.example.supplychainback.API.Model.LoginBody;
import com.example.supplychainback.API.Model.RegistrationBody;
import com.example.supplychainback.Service.UserService;
import com.example.supplychainback.model.LocalUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @Test
    void loginUserTest(){
        UserService userService = new UserService();
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("testUser");
        loginBody.setPassword("password123");
        String jwtToken = userService.loginUser(loginBody);
        assertNotNull(jwtToken, "JWT token should not be null");
    }
}
