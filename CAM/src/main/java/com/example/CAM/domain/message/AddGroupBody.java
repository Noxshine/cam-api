package com.example.CAM.domain.message;

public class AddGroupBody {
    private String displayName;
    private String groupAccessRightInfo;

    public AddGroupBody(String displayName, String groupAccessRightInfo) {
        this.displayName = displayName;
        this.groupAccessRightInfo = groupAccessRightInfo;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGroupAccessRightInfo() {
        return groupAccessRightInfo;
    }

    public void setGroupAccessRightInfo(String groupAccessRightInfo) {
        this.groupAccessRightInfo = groupAccessRightInfo;
    }
}
