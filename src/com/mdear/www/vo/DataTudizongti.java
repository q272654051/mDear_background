package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataTudizongti entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_tudizongti", catalog = "shiyan")
public class DataTudizongti implements java.io.Serializable {

	// Fields

	private Integer id;
	private String shijian;
	private String city;
	private String yongtu;
	private Integer zongshu;
	private double tudichurangjin;
	private double jiansheyongdimianji;
	private double guihuajianzhumianji;
	private double pingjunyijialv;
	private Integer userid;

	// Constructors

	/** default constructor */
	public DataTudizongti() {
	}

	/** full constructor */
	public DataTudizongti(String shijian, String city, String yongtu,
			Integer zongshu, double tudichurangjin, double jiansheyongdimianji,
			double guihuajianzhumianji, double pingjunyijialv, Integer userid) {
		this.shijian = shijian;
		this.city = city;
		this.yongtu = yongtu;
		this.zongshu = zongshu;
		this.tudichurangjin = tudichurangjin;
		this.jiansheyongdimianji = jiansheyongdimianji;
		this.guihuajianzhumianji = guihuajianzhumianji;
		this.pingjunyijialv = pingjunyijialv;
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

	@Column(name = "yongtu", length = 100)
	public String getYongtu() {
		return this.yongtu;
	}

	public void setYongtu(String yongtu) {
		this.yongtu = yongtu;
	}

	@Column(name = "zongshu")
	public Integer getZongshu() {
		return this.zongshu;
	}

	public void setZongshu(Integer zongshu) {
		this.zongshu = zongshu;
	}

	@Column(name = "tudichurangjin", precision = 22, scale = 0)
	public double getTudichurangjin() {
		return this.tudichurangjin;
	}

	public void setTudichurangjin(double tudichurangjin) {
		this.tudichurangjin = tudichurangjin;
	}

	@Column(name = "jiansheyongdimianji", precision = 22, scale = 0)
	public double getJiansheyongdimianji() {
		return this.jiansheyongdimianji;
	}

	public void setJiansheyongdimianji(double jiansheyongdimianji) {
		this.jiansheyongdimianji = jiansheyongdimianji;
	}

	@Column(name = "guihuajianzhumianji", precision = 22, scale = 0)
	public double getGuihuajianzhumianji() {
		return this.guihuajianzhumianji;
	}

	public void setGuihuajianzhumianji(double guihuajianzhumianji) {
		this.guihuajianzhumianji = guihuajianzhumianji;
	}

	@Column(name = "pingjunyijialv", precision = 22, scale = 0)
	public double getPingjunyijialv() {
		return this.pingjunyijialv;
	}

	public void setPingjunyijialv(double pingjunyijialv) {
		this.pingjunyijialv = pingjunyijialv;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}