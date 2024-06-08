package com.example.supplychainback.API.Controller.Auth;

import com.example.supplychainback.API.Model.LoginBody;
import com.example.supplychainback.API.Model.RegistrationBody;
import com.example.supplychainback.API.Model.loginResponse;
import com.example.supplychainback.Service.UserService;
import com.example.supplychainback.exception.useralreadyexists;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")

/**
 * The Authentication Controller class is responsible to get the data for login and registration from the frontend and
 * if the details are as expected it returns an Ok response
 * The class uses two other classes registrationBody and loginBody to process the front-end data.
 * Additionally it calls the registerUser and loginuser methods from UserService class
 */
public class authenticationController {

    public authenticationController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try{
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        }catch (useralreadyexists ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<loginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){

        String jwt = userService.loginUser(loginBody);
        if(jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            loginResponse response = new loginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }
}
