package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Code entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "code", catalog = "shiyan")
public class Code implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer dmid;
    private Integer dmz;
    private String  dmsm;
    private String  dmsm1;
    private String  dmsm2;
    private Integer pid;

    // Constructors

    /** default constructor */
    public Code() {
    }

    /** minimal constructor */
    public Code(Integer pid) {
        this.pid = pid;
    }

    /** full constructor */
    public Code(Integer dmz, String dmsm, String dmsm1, String dmsm2,
            Integer pid) {
        this.dmz = dmz;
        this.dmsm = dmsm;
        this.dmsm1 = dmsm1;
        this.dmsm2 = dmsm2;
        this.pid = pid;
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

    @Column(name = "dmz")
    public Integer getDmz() {
        return this.dmz;
    }

    public void setDmz(Integer dmz) {
        this.dmz = dmz;
    }

    @Column(name = "dmsm", length = 100)
    public String getDmsm() {
        return this.dmsm;
    }

    public void setDmsm(String dmsm) {
        this.dmsm = dmsm;
    }

    @Column(name = "dmsm1", length = 100)
    public String getDmsm1() {
        return this.dmsm1;
    }

    public void setDmsm1(String dmsm1) {
        this.dmsm1 = dmsm1;
    }

    @Column(name = "dmsm2", length = 100)
    public String getDmsm2() {
        return this.dmsm2;
    }

    public void setDmsm2(String dmsm2) {
        this.dmsm2 = dmsm2;
    }

    @Column(name = "pid", nullable = false)
    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name = "dmid", nullable = false)
    public Integer getDmid() {
        return dmid;
    }

    public void setDmid(Integer dmid) {
        this.dmid = dmid;
    }

}