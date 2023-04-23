package org.launchcode.TasteBuddiesServer.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationFormDTO extends LoginFormDTO {

    private String username;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password must be 6-30 characters long.")
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
