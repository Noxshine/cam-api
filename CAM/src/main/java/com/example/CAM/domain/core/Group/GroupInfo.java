package com.example.CAM.domain.core.Group;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

// information of a group
@Entity
@Table(name = "cam_group_info")
public class GroupInfo {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "value")
    private UUID value;
    @Column(name = "ref")
    private String ref;
    @Column(name = "display_name")
    private String displayName;
    @JsonIgnore
    @Column(name = "group_id")
    private Long groupId;

    public GroupInfo(Long id, UUID value, String ref, String displayName, Long groupId) {
        this.id = id;
        this.value = value;
        this.ref = ref;
        this.displayName = displayName;
        this.groupId = groupId;
    }

    public GroupInfo() {
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}