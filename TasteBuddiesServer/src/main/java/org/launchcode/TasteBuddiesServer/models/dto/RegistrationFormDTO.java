package org.launchcode.TasteBuddiesServer.models.dto;

public class RegistrationFormDTO extends LoginFormDTO {

    // TODO add validation
    private String displayName;

    public RegistrationFormDTO(String email, String password, String displayName) {
        super(email, password);
        this.displayName = displayName;
    }

    public RegistrationFormDTO(String displayName) {
        this.displayName = displayName;
    }

    public RegistrationFormDTO() { }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
