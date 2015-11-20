package com.mdear.www.vo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dengbojing
 * @date 2015-10-20
 * @description
 */
@Entity
@Table(name = "user_column")
public class UserColumn {
    private int id;
    private int user_id;
    private int column_id;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "user_id")
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    @Column(name = "column_id")
    public int getColumn_id() {
        return column_id;
    }
    public void setColumn_id(int column_id) {
        this.column_id = column_id;
    }
}
