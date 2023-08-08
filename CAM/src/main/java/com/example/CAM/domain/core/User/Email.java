package com.example.CAM.domain.core.User;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cam_emails")
public class Email {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JsonIgnore
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "email_value")
    private String value;
    @Column(name = "email_type")
    private String type;
    @Column(name = "email_primary")
    private Boolean primary;

    public Email(Long userId, String value, String type, Boolean primary) {
        this.userId = userId;
        this.value = value;
        this.type = type;
        this.primary = primary;
    }

    public Email() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
