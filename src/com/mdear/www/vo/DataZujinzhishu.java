package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataZujinzhishu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_zujinzhishu", catalog = "shiyan")
public class DataZujinzhishu implements java.io.Serializable {

	// Fields

	private Integer id;
	private String shijian;
	private String city;
	private String wuyeleixing;
	private double zujinzhishu;
	private double huanbi;
	private Integer userid;

	// Constructors

	/** default constructor */
	public DataZujinzhishu() {
	}

	/** full constructor */
	public DataZujinzhishu(String shijian, String city, String wuyeleixing,
			double zujinzhishu, double huanbi, Integer userid) {
		this.shijian = shijian;
		this.city = city;
		this.wuyeleixing = wuyeleixing;
		this.zujinzhishu = zujinzhishu;
		this.huanbi = huanbi;
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

	@Column(name = "wuyeleixing", length = 100)
	public String getWuyeleixing() {
		return this.wuyeleixing;
	}

	public void setWuyeleixing(String wuyeleixing) {
		this.wuyeleixing = wuyeleixing;
	}

	@Column(name = "zujinzhishu", precision = 22, scale = 0)
	public double getZujinzhishu() {
		return this.zujinzhishu;
	}

	public void setZujinzhishu(double zujinzhishu) {
		this.zujinzhishu = zujinzhishu;
	}

	@Column(name = "huanbi", precision = 22, scale = 0)
	public double getHuanbi() {
		return this.huanbi;
	}

	public void setHuanbi(double huanbi) {
		this.huanbi = huanbi;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}