package org.launchcode.TasteBuddiesServer.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class RegistrationFormDTO extends LoginFormDTO {

    private String displayName;

}
