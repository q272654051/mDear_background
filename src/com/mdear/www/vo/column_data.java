package com.mdear.www.vo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "column_data", catalog = "shiyan")
public class column_data implements java.io.Serializable {
	private Integer id;
	private String table_name;
	private String column_pinyin;
	private String column_name;
	private String table_pinyin;
	private Integer isVisable;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "table_name", length = 100)
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	@Column(name = "column_pinyin", length = 100)
	public String getColumn_pinyin() {
		return column_pinyin;
	}
	public void setColumn_pinyin(String column_pinyin) {
		this.column_pinyin = column_pinyin;
	}
	@Column(name = "column_name", length = 100)
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	@Column(name = "table_pinyin", length = 100)
	public String getTable_pinyin() {
		return table_pinyin;
	}
	public void setTable_pinyin(String table_pinyin) {
		this.table_pinyin = table_pinyin;
	}
	@Column(name = "isVisable", length = 100)
	public Integer getIsVisable() {
		return isVisable;
	}
	public void setIsVisable(Integer isVisable) {
		this.isVisable = isVisable;
	}
}