package com.example.CAM.domain.message;

public class UserInfoBody {
    Long userId;

    public UserInfoBody(){};
    public UserInfoBody(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
