package com.shnnny.notBlog.model.po;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blog_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * user表主键
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 昵称-艺名
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 用户的邮箱
     */
    private String email;

    /**
     * 用户的主页
     */
    private String homeUrl;


    /**
     * 用户注册时的GMT unix时间戳
     */
    private Long created;

    /**
     * 最后活动时间
     */
    private Long activated;


    /**
     * 用户头像地址
     */
    private String Avatar;

    /**
     * 用户所属的角色
     */
    private String roleId;

    /**
     * 保留时间
     */
    private String outDate;

    /**
     * 校验的密钥
     */
    private String validataCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(length = 40)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Column(length = 40)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(length = 40)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Column(length = 40)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 100)
    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    @Column(length = 40)
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Column(length = 40)
    public Long getActivated() {
        return activated;
    }

    public void setActivated(Long activated) {
        this.activated = activated;
    }

    @Column(length = 200)
    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    @Column(length = 20)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(length = 20)
    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Column(length = 20)
    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }
}
