package com.example.CAM.domain.core.Group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cam_user_group")
public class UserGroup {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "user_info_id")
    private Long user_info_id;
    @Column(name = "group_info_id")
    private Long group_info_id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "group_id")
    private Long group_id;

    public UserGroup(Long id, Long user_info_id, Long group_info_id, Long user_id, Long group_id) {
        this.id = id;
        this.user_info_id = user_info_id;
        this.group_info_id = group_info_id;
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public UserGroup(Long user_info_id, Long group_info_id, Long user_id, Long group_id) {
        this.user_info_id = user_info_id;
        this.group_info_id = group_info_id;
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public UserGroup() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_info_id() {
        return user_info_id;
    }

    public void setUser_info_id(Long user_info_id) {
        this.user_info_id = user_info_id;
    }

    public Long getGroup_info_id() {
        return group_info_id;
    }

    public void setGroup_info_id(Long group_info_id) {
        this.group_info_id = group_info_id;
    }
}
