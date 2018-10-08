package com.shnnny.notBlog.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blog_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * user表主键
     */
    private Integer uid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户的邮箱
     */
    private String email;

    /**
     * 用户的主页
     */
    private String homeUrl;

    /**
     * 用户显示的名称
     */
    private String screenName;

    /**
     * 用户注册时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 最后活动时间
     */
    private Integer activated;

    /**
     * 上次登录最后活跃时间
     */
    private Integer logged;

    /**
     * 用户类型　游客　－　非游客
     */
    private String userType;

    /**
     * 用户所属的角色
     */
    private String roleId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Column(length = 40)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Column(length = 40)
    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @Column(length = 20)
    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    @Column(length = 20)
    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    @Column(length = 20)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(length = 20)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
