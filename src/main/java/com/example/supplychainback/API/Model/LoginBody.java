package com.example.supplychainback.API.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Login Body with username and password to reference in authenticationController
 */
public class LoginBody {
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;


}
