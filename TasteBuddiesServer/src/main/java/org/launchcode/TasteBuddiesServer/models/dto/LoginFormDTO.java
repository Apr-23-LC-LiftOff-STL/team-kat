package org.launchcode.TasteBuddiesServer.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginFormDTO {

    @NotNull(message = "Email is required.")
    @NotBlank(message = "Email is required.")
    @Email(message = "A valid email is required.")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password must be 6-30 characters long.")
    private String password;

}
