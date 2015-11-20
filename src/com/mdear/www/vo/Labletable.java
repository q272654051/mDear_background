package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Labletable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "labletable", catalog = "shiyan")
public class Labletable implements java.io.Serializable {

	// Fields

	private Integer id;
	private String lable;
	private Integer status;
	private Integer pid;

	// Constructors

	/** default constructor */
	public Labletable() {
	}

	/** full constructor */
	public Labletable(String lable, Integer status, Integer pid) {
		this.lable = lable;
		this.status = status;
		this.pid = pid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "lable", length = 50)
	public String getLable() {
		return this.lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}