package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Touzihuibao entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "touzihuibao", catalog = "shiyan")
public class Touzihuibao implements java.io.Serializable {

	// Fields

	private Integer id;
	private String city;
	private String type;
	private String zushoubi;
	private String changqitouzi;
	private String wunianzhuanshou;
	private String shijian;
	private Integer pnum;
	private String zuobiao;

	// Constructors

	/** default constructor */
	public Touzihuibao() {
	}

	/** full constructor */
	public Touzihuibao(String city, String type, String zushoubi,
			String changqitouzi, String wunianzhuanshou, String shijian,
			Integer pnum, String zuobiao) {
		this.city = city;
		this.type = type;
		this.zushoubi = zushoubi;
		this.changqitouzi = changqitouzi;
		this.wunianzhuanshou = wunianzhuanshou;
		this.shijian = shijian;
		this.pnum = pnum;
		this.zuobiao = zuobiao;
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

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "zushoubi", length = 500)
	public String getZushoubi() {
		return this.zushoubi;
	}

	public void setZushoubi(String zushoubi) {
		this.zushoubi = zushoubi;
	}

	@Column(name = "changqitouzi", length = 500)
	public String getChangqitouzi() {
		return this.changqitouzi;
	}

	public void setChangqitouzi(String changqitouzi) {
		this.changqitouzi = changqitouzi;
	}

	@Column(name = "wunianzhuanshou", length = 500)
	public String getWunianzhuanshou() {
		return this.wunianzhuanshou;
	}

	public void setWunianzhuanshou(String wunianzhuanshou) {
		this.wunianzhuanshou = wunianzhuanshou;
	}

	@Column(name = "shijian", length = 500)
	public String getShijian() {
		return this.shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	@Column(name = "pnum")
	public Integer getPnum() {
		return this.pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	@Column(name = "zuobiao")
	public String getZuobiao() {
		return this.zuobiao;
	}

	public void setZuobiao(String zuobiao) {
		this.zuobiao = zuobiao;
	}

}