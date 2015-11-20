package com.mdear.www.vo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "shiyan")
public class News implements java.io.Serializable {

	// Fields

	private Integer id=0;  //
	private String lyName;  //來源
	private String title;  //標題
	private Integer ifzq;  //是否輪播
	private String keyword;  //新聞關鍵字
	private Integer userid;  //撰寫人id
	private String zqsj;  //
	private String province; //省 
	private String city;  //城市
	private String smalltype;  //新聞小類別
	private String imageurl;  //圖片路徑
	private String type;  // 新聞大類別
	private String cont;  //正文内容
	private String fabudate;   //發佈時間
	private Integer status;  //狀態(審核不通過,發佈)
	private Integer djl;  //點擊量
	private String zhaiyao;  //摘要
	private String editdate; //最後編輯時間
	private String createdate;  //新聞第一次創建時間
	private String pdfurl;  //pdf路徑
	private Integer zan;  //點贊數量
	private Integer tucao;  //吐槽數量
	private String zuobiao; //坐標
	private Integer iflunbo;  //是否輪播
	private Integer shenheid; //審核人id
	private String tuihuiyuanyin;  //退回原因(審核不通過被退回)
	private String shouhuiyuanyin;  //收回原因(發佈收回)
	private String huituistatus;   //回退狀態
	private String startTime;  //查询用
	private String endTime;   //查询用
	private String plateformId;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** full constructor */
	public News(String lyName, String title, Integer ifzq, String keyword,
			Integer userid, String zqsj, String city, String smalltype,
			String imageurl, String type, String cont, String fabudate,
			Integer status, Integer djl, String zhaiyao, String editdate,
			String createdate, String pdfurl, Integer zan, Integer tucao,
			String zuobiao, Integer iflunbo, Integer shenheid,
			String tuihuiyuanyin, String shouhuiyuanyin) {
		this.lyName = lyName;
		this.title = title;
		this.ifzq = ifzq;
		this.keyword = keyword;
		this.userid = userid;
		this.zqsj = zqsj;
		this.city = city;
		this.smalltype = smalltype;
		this.imageurl = imageurl;
		this.type = type;
		this.cont = cont;
		this.fabudate = fabudate;
		this.status = status;
		this.djl = djl;
		this.zhaiyao = zhaiyao;
		this.editdate = editdate;
		this.createdate = createdate;
		this.pdfurl = pdfurl;
		this.zan = zan;
		this.tucao = tucao;
		this.zuobiao = zuobiao;
		this.iflunbo = iflunbo;
		this.shenheid = shenheid;
		this.tuihuiyuanyin = tuihuiyuanyin;
		this.shouhuiyuanyin = shouhuiyuanyin;
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

	@Column(name = "lyName")
	public String getLyName() {
		return this.lyName;
	}

	public void setLyName(String lyName) {
		this.lyName = lyName;
	}

	@Column(name = "Title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ifzq")
	public Integer getIfzq() {
		return this.ifzq;
	}

	public void setIfzq(Integer ifzq) {
		this.ifzq = ifzq;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "zqsj")
	public String getZqsj() {
		return this.zqsj;
	}

	public void setZqsj(String zqsj) {
		this.zqsj = zqsj;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "province", length = 50)
	public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "smalltype", length = 50)
	public String getSmalltype() {
		return this.smalltype;
	}

	public void setSmalltype(String smalltype) {
		this.smalltype = smalltype;
	}

	@Column(name = "imageurl")
	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Column(name = "type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "cont")
	public String getCont() {
		return this.cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	@Column(name = "fabudate")
	public String getFabudate() {
		return this.fabudate;
	}

	public void setFabudate(String fabudate) {
		this.fabudate = fabudate;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "djl")
	public Integer getDjl() {
		return this.djl;
	}

	public void setDjl(Integer djl) {
		this.djl = djl;
	}

	@Column(name = "zhaiyao")
	public String getZhaiyao() {
		return this.zhaiyao;
	}

	public void setZhaiyao(String zhaiyao) {
		this.zhaiyao = zhaiyao;
	}

	@Column(name = "editdate")
	public String getEditdate() {
		return this.editdate;
	}

	public void setEditdate(String editdate) {
		this.editdate = editdate;
	}

	@Column(name = "createdate")
	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	@Column(name = "pdfurl")
	public String getPdfurl() {
		return this.pdfurl;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}

	@Column(name = "zan")
	public Integer getZan() {
		return this.zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	@Column(name = "tucao")
	public Integer getTucao() {
		return this.tucao;
	}

	public void setTucao(Integer tucao) {
		this.tucao = tucao;
	}

	@Column(name = "zuobiao")
	public String getZuobiao() {
		return this.zuobiao;
	}

	public void setZuobiao(String zuobiao) {
		this.zuobiao = zuobiao;
	}

	@Column(name = "iflunbo")
	public Integer getIflunbo() {
		return this.iflunbo;
	}

	public void setIflunbo(Integer iflunbo) {
		this.iflunbo = iflunbo;
	}

	@Column(name = "shenheid")
	public Integer getShenheid() {
		return this.shenheid;
	}

	public void setShenheid(Integer shenheid) {
		this.shenheid = shenheid;
	}

	@Column(name = "tuihuiyuanyin", length = 1000)
	public String getTuihuiyuanyin() {
		return this.tuihuiyuanyin;
	}

	public void setTuihuiyuanyin(String tuihuiyuanyin) {
		this.tuihuiyuanyin = tuihuiyuanyin;
	}

	@Column(name = "shouhuiyuanyin", length = 1000)
	public String getShouhuiyuanyin() {
		return this.shouhuiyuanyin;
	}

	public void setShouhuiyuanyin(String shouhuiyuanyin) {
		this.shouhuiyuanyin = shouhuiyuanyin;
	}
	
	
	@Column(name = "huituiStatus", length = 11)
    public String getHuituistatus() {
        return huituistatus;
    }

    public void setHuituistatus(String huituistatus) {
        this.huituistatus = huituistatus;
    }
    
    
    @Transient
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    @Transient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name = "plateformId", length = 11)
    public String getPlateformId() {
        return plateformId;
    }

    public void setPlateformId(String plateformId) {
        this.plateformId = plateformId;
    }

}