package com.example.CAM.domain.core.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cam_user_info")
public class UserInfo {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "value")
    private UUID value;// userID
    @Column(name = "ref")
    private String ref;
    @Column(name = "display_name")
    private String display_name;
    @JsonIgnore
    @Column(name = "user_id")
    private Long userId;

    public UserInfo(Long id, UUID value, String ref, String display_name, Long userId) {
        this.id = id;
        this.value = value;
        this.ref = ref;
        this.display_name = display_name;
        this.userId = userId;
    }

    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
