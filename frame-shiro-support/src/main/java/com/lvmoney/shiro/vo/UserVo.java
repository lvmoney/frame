package com.lvmoney.shiro.vo;

import java.io.Serializable;

public class UserVo implements Serializable {
    private static final long serialVersionUID = -1394871563448242842L;

    private String userId;

    private String userName;

    private String passWord;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return pass_word
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}