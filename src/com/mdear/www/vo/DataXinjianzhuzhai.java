package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataXinjianzhuzhai entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_xinjianzhuzhai", catalog = "shiyan")
public class DataXinjianzhuzhai implements java.io.Serializable {

	// Fields

	private Integer id;
	private String shijian;
	private String city;
	private Integer xiaoshoutaoshu;
	private double xiaoshoumianji;
	private double xiaoshoujiage;
	private Integer userid;

	// Constructors

	/** default constructor */
	public DataXinjianzhuzhai() {
	}

	/** full constructor */
	public DataXinjianzhuzhai(String shijian, String city,
			Integer xiaoshoutaoshu, double xiaoshoumianji,
			double xiaoshoujiage, Integer userid) {
		this.shijian = shijian;
		this.city = city;
		this.xiaoshoutaoshu = xiaoshoutaoshu;
		this.xiaoshoumianji = xiaoshoumianji;
		this.xiaoshoujiage = xiaoshoujiage;
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

	@Column(name = "xiaoshoutaoshu")
	public Integer getXiaoshoutaoshu() {
		return this.xiaoshoutaoshu;
	}

	public void setXiaoshoutaoshu(Integer xiaoshoutaoshu) {
		this.xiaoshoutaoshu = xiaoshoutaoshu;
	}

	@Column(name = "xiaoshoumianji", precision = 22, scale = 0)
	public double getXiaoshoumianji() {
		return this.xiaoshoumianji;
	}

	public void setXiaoshoumianji(double xiaoshoumianji) {
		this.xiaoshoumianji = xiaoshoumianji;
	}

	@Column(name = "xiaoshoujiage", precision = 22, scale = 0)
	public double getXiaoshoujiage() {
		return this.xiaoshoujiage;
	}

	public void setXiaoshoujiage(double xiaoshoujiage) {
		this.xiaoshoujiage = xiaoshoujiage;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}