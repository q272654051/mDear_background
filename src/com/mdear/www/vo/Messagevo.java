package com.mdear.www.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Messagevo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "messagevo", catalog = "shiyan")
public class Messagevo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String message;
	private Integer messageStatus;
	private Integer userid;
	private Date shijian;
	private Integer status;
	private Integer shenherenid;
	private Integer newsid;

	// Constructors

	/** default constructor */
	public Messagevo() {
	}

	/** full constructor */
	public Messagevo(String message, Integer messageStatus, Integer userid,
			Date shijian, Integer status,Integer shenherenid,Integer newsid) {
		this.message = message;
		this.messageStatus = messageStatus;
		this.userid = userid;
		this.shijian = shijian;
		this.status = status;
		this.shenherenid=shenherenid;
		this.newsid=newsid;
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

	@Column(name = "message", length = 500)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "messageStatus")
	public Integer getMessageStatus() {
		return this.messageStatus;
	}

	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "shijian", length = 19)
	public Date getShijian() {
		return this.shijian;
	}

	public void setShijian(Date shijian) {
		this.shijian = shijian;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "shenherenid")
	public Integer getShenherenid() {
		return this.shenherenid;
	}

	public void setShenherenid(Integer shenherenid) {
		this.shenherenid = shenherenid;
	}
	
	@Column(name = "newsid")
	public Integer getNewsid() {
		return this.newsid;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}
}