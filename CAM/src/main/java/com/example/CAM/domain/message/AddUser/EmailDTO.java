package com.example.CAM.domain.message.AddUser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailDTO {
    private String value;
    private String type;
    private Boolean primary;
    public EmailDTO(String value, String type, Boolean primary) {
        this.value = value;
        this.type = type;
        this.primary = primary;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}
