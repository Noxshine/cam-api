package com.example.CAM.domain.core.Group;


import com.example.CAM.domain.core.User.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Map.entry;

@Data
@Entity
@Table(name = "cam_groups")
public class Group {
    @Transient
    private List<String> schemas = List.of("urn:ietf:params:scim:schemas:core:2.0:Group",
            "urn:ietf:params:scim:schemas:extension:cam:2.0:Group");
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "external_id")
    private UUID externalId;

    // meta
    @Column(name = "resource_type")
    private String resourceType;
    @Column(name = "created")
    private Instant created ;
    @Column(name = "last_modified")
    private Instant lastModified ;
    @Column(name = "display_name")
    private String displayName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cam_user_group", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_info_id") })
    private List<UserInfo> members;

    @Column(name = "group_access_right_info")
    private String groupAccessRightInfo;

    public Group(Long id, UUID externalId, String resourceType, Instant created, Instant lastModified, String displayName, List<UserInfo> members, String groupAccessRightInfo) {
        this.id = id;
        this.externalId = externalId;
        this.resourceType = resourceType;
        this.created = created;
        this.lastModified = lastModified;
        this.displayName = displayName;
        this.members = members;
        this.groupAccessRightInfo = groupAccessRightInfo;
    }

    public Group() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<UserInfo> getMembers() {
        return members;
    }

    public void setMembers(List<UserInfo> members) {
        this.members = members;
    }

    public String getGroupAccessRightInfo() {
        return groupAccessRightInfo;
    }

    public void setGroupAccessRightInfo(String groupAccessRightInfo) {
        this.groupAccessRightInfo = groupAccessRightInfo;
    }

    public String toJson(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(
                    Map.ofEntries(
                            entry("schemas", (this.schemas != null) ? this.schemas : ""), entry("id", this.id), entry("externalId", this.externalId),
                            entry("meta", Map.of("resourceType", this.resourceType, "created", this.created.toString(),"lastModified",(this.lastModified != null) ? this.lastModified : "")),
                            entry("displayName", this.displayName), entry("members", this.members),
                            entry("urn:ietf:params:scim:schemas:extension:cam:2.0:Group",this.groupAccessRightInfo)
                    )
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // Handle the error appropriately in your code
        }
    }
}
