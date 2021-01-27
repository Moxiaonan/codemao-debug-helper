package com.xy.debug.helper.entity;

/**
 * TODO
 *
 * @author moxiaonan
 * @since 2021/1/26
 */
public class UserInfo {
    private long userId;
    private String userName;

    public UserInfo(long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserInfo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
