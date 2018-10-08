package com.shnnny.notBlog.bean;

import javax.persistence.*;
import java.io.Serializable;
/**
 *角色信息，这里主要体现三种不同的角色，同时也可以定义不同的角色对应不同的权限
 */
@Entity
@Table(name = "blog_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色创建
     */
    private Integer created;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(length = 40)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(length = 20)
    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}
