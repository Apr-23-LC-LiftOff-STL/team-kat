package org.launchcode.TasteBuddiesServer.models.dto;

public class JoinEventDTO {
    private String entryCode;
    public JoinEventDTO(String entryCode){
        this.entryCode = entryCode;
    }
    public JoinEventDTO(){}

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }
}
