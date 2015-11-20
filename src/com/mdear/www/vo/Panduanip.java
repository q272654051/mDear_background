package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Panduanip entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "panduanip", catalog = "shiyan")
public class Panduanip implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer newsid;
	private String ipaddress;
	private String type;
	private String shijian;

	// Constructors

	/** default constructor */
	public Panduanip() {
	}

	/** full constructor */
	public Panduanip(Integer newsid, String ipaddress, String type,
			String shijian) {
		this.newsid = newsid;
		this.ipaddress = ipaddress;
		this.type = type;
		this.shijian = shijian;
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

	@Column(name = "newsid")
	public Integer getNewsid() {
		return this.newsid;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}

	@Column(name = "ipaddress", length = 500)
	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	@Column(name = "type", length = 500)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "shijian", length = 500)
	public String getShijian() {
		return this.shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

}