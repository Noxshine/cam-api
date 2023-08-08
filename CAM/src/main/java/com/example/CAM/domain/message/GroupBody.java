package com.example.CAM.domain.message;

public class GroupBody {
    private Long groupId;

    public GroupBody() {}

    public GroupBody(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
