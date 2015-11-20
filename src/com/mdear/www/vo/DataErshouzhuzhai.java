package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataErshouzhuzhai entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_ershouzhuzhai", catalog = "shiyan")
public class DataErshouzhuzhai implements java.io.Serializable {

	// Fields

	private Integer id;
	private String shijian;
	private String city;
	private Integer chengjiaotaoshu;
	private double shichangjunjia;
	private Integer userid;

	// Constructors

	/** default constructor */
	public DataErshouzhuzhai() {
	}

	/** full constructor */
	public DataErshouzhuzhai(String shijian, String city,
			Integer chengjiaotaoshu, double shichangjunjia, Integer userid) {
		this.shijian = shijian;
		this.city = city;
		this.chengjiaotaoshu = chengjiaotaoshu;
		this.shichangjunjia = shichangjunjia;
		this.userid = userid;
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

	@Column(name = "shijian", length = 100)
	public String getShijian() {
		return this.shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "chengjiaotaoshu")
	public Integer getChengjiaotaoshu() {
		return this.chengjiaotaoshu;
	}

	public void setChengjiaotaoshu(Integer chengjiaotaoshu) {
		this.chengjiaotaoshu = chengjiaotaoshu;
	}

	@Column(name = "shichangjunjia", precision = 22, scale = 0)
	public double getShichangjunjia() {
		return this.shichangjunjia;
	}

	public void setShichangjunjia(double shichangjunjia) {
		this.shichangjunjia = shichangjunjia;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}