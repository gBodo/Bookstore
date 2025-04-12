package com.example.Bookstore.RequestBody;

import jakarta.validation.constraints.*;

public class LoginBody {
    @NotNull
    @NotBlank
    @Size(min = 3 , max = 255)
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 8 , max = 100)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
