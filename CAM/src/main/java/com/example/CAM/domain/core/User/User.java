package com.example.CAM.domain.core.User;


import com.example.CAM.domain.core.Group.GroupInfo;
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
@Table(name = "cam_users")
public class User  {
    @Transient
    private List<String> schemas = List.of("urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
            "urn:ietf:params:scim:schemas:extension:cam:2.0:User");
    @Id
    @JsonIgnore
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

    @Column(name = "user_name")
    private String userName;
    @Column(name = "display_name")
    private String displayName;

    //name
    @Column(name = "formatted_name")
    private String formatted;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "given_name")
    private String givenName;

    @Column(name = "active")
    private Boolean active;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Email> email ;
    @Column(name = "profile_url")
    private String profileUrl;
    @Column(name = "title")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cam_user_group", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "group_info_id") })
    private List<GroupInfo> groups;

    // enterprise user
    @Column(name = "organization")
    private String organization;
    @Column(name = "division")
    private String division;
    @Column(name = "department")
    private String department;

    // enterprise manager
    @Column(name = "manager_value")
    private String manager_value;
    @Column(name = "manager_ref")
    private String manager_ref;
    @Column(name = "manager_display_name")
    private String manager_displayName;

    // cam user
    @Column(name = "last_login")
    private Instant lastLogin;
    @Column(name = "last_password_changed")
    private Instant lastPasswordChanged;
    @Column(name = "is_privileged")
    private Boolean isPrivileged;

    public User() {

    }

    public User(Long id, UUID externalId, String resourceType, Instant created, Instant lastModified, String userName, String displayName, String formatted, String familyName, String givenName, Boolean active, List<Email> email, String profileUrl, String title, UserType userType, List<GroupInfo> groups, String organization, String division, String department, String manage_value, String manager_ref, String manager_displayName, Instant lastLogin, Instant lastPasswordChanged, Boolean isPrivileged) {
        this.id = id;
        this.externalId = externalId;
        this.resourceType = resourceType;
        this.created = created;
        this.lastModified = lastModified;
        this.userName = userName;
        this.displayName = displayName;
        this.formatted = formatted;
        this.familyName = familyName;
        this.givenName = givenName;
        this.active = active;
        this.email = email;
        this.profileUrl = profileUrl;
        this.title = title;
        this.userType = userType;
        this.groups = groups;
        this.organization = organization;
        this.division = division;
        this.department = department;
        this.manager_value = manage_value;
        this.manager_ref = manager_ref;
        this.manager_displayName = manager_displayName;
        this.lastLogin = lastLogin;
        this.lastPasswordChanged = lastPasswordChanged;
        this.isPrivileged = isPrivileged;
    }


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<GroupInfo> getGroups() {return groups;}

    public void setGroups(List<GroupInfo> groups) {this.groups = groups;}

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager_value() {
        return manager_value;
    }

    public void setManager_value(String manager_value) {
        this.manager_value = manager_value;
    }

    public String getManager_ref() {
        return manager_ref;
    }

    public void setManager_ref(String manager_ref) {
        this.manager_ref = manager_ref;
    }

    public String getManager_displayName() {
        return manager_displayName;
    }

    public void setManager_displayName(String manager_displayName) {
        this.manager_displayName = manager_displayName;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Instant getLastPasswordChanged() {
        return lastPasswordChanged;
    }

    public void setLastPasswordChanged(Instant lastPasswordChanged) {
        this.lastPasswordChanged = lastPasswordChanged;
    }

    public Boolean getPrivileged() {
        return isPrivileged;
    }

    public void setPrivileged(Boolean privileged) {
        isPrivileged = privileged;
    }

    public String toJson(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(
                    Map.ofEntries(
                            entry("schemas", (this.schemas != null) ? this.schemas : ""), entry("id", this.id), entry("externalId", this.externalId),
                            entry("meta", Map.of("resourceType", this.resourceType, "created", this.created.toString(),"lastModified",(this.lastModified != null) ? this.lastModified : "")),
                            entry("userName", this.userName), entry("displayName", this.displayName),
                            entry("name", Map.of("formatted", this.formatted, "familyName", this.familyName, "givenName", this.givenName)),
                            entry("active", this.active), entry("emails", this.email), entry("profileUrl", this.profileUrl),
                            entry("title", this.title), entry("userType", this.userType), entry("groups", this.groups),
                            entry("urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
                                    Map.of("organization",this.organization,"division",this.division,
                                    "department", this.department, "manager",Map.of("value",(this.manager_value != null) ? this.manager_value : "","$ref",this.manager_ref,"displayName",this.manager_displayName))),
                            entry("urn:ietf:params:scim:schemas:extension:cam:2.0:User",
                                    Map.of("lastLogin",(this.lastLogin != null) ? this.lastLogin : "","lastPasswordChanged",(this.lastPasswordChanged != null) ? this.lastPasswordChanged : "","isPrivileged",this.isPrivileged))
                    )
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // Handle the error appropriately in your code
        }
    }
}
