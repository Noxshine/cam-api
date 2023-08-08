package com.example.CAM.domain.message.AddUser;

import com.example.CAM.domain.core.User.User;
import com.example.CAM.domain.core.User.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public class AddUserBody {

    private String resourceType;
    private String userName;
    private String displayName;
    private String formatted;
    private String familyName;
    private String givenName;
    @JsonProperty("emails")
    List<EmailDTO> email;
    private String profileUrl;
    private String title;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String organization;
    private String division;
    private String department;
    private String manager_value;
    private String manager_ref;
    private String manager_displayName;

    public AddUserBody(String resourceType, String userName, String displayName, String formatted, String familyName, String givenName, List<EmailDTO> email, String profileUrl, String title, UserType userType, String organization, String division, String department, String manager_value, String manager_ref, String manager_displayName) {
        this.resourceType = resourceType;
        this.userName = userName;
        this.displayName = displayName;
        this.formatted = formatted;
        this.familyName = familyName;
        this.givenName = givenName;
        this.email = email;
        this.profileUrl = profileUrl;
        this.title = title;
        this.userType = userType;
        this.organization = organization;
        this.division = division;
        this.department = department;
        this.manager_value = manager_value;
        this.manager_ref = manager_ref;
        this.manager_displayName = manager_displayName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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

    public List<EmailDTO> getEmail() {
        return email;
    }

    public void setEmail(List<EmailDTO> email) {
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
}
