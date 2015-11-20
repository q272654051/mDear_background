package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "shiyan")
public class Role implements java.io.Serializable {

    // Fields

    private Integer id;
    private String  roleName;
    private String  roleComment;

    // Constructors

    /** default constructor */
    public Role() {
    }

    /** minimal constructor */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    /** full constructor */
    public Role(String roleName, String roleComment) {
        this.roleName = roleName;
        this.roleComment = roleComment;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "roleName", nullable = false)
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "roleComment", length = 65535)
    public String getRoleComment() {
        return this.roleComment;
    }

    public void setRoleComment(String roleComment) {
        this.roleComment = roleComment;
    }
    
    public boolean equals(Role role){
        if(role == null){
            return false;
        }
        if(this.getId() == role.getId()){
            return true;
        }else{
            return false;
        }
    }
}