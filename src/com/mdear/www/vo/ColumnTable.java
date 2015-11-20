package com.mdear.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ColumnA entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "column_a", catalog = "shiyan")
public class ColumnTable implements java.io.Serializable {

	// Fields

	private Integer id;
	private String columnName;
	private Integer pid;
	private Integer status;

	// Constructors

	/** default constructor */
	public ColumnTable() {
	}

	/** full constructor */
	public ColumnTable(String columnName, Integer pid, Integer status) {
		this.columnName = columnName;
		this.pid = pid;
		this.status = status;
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

	@Column(name = "column_name", length = 50)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public boolean equals(ColumnTable column){
	    if(column == null){
	        return false;
	    }
	    if(this.getId() == column.getId()){
	        return true;
	    }else{
	        return false;
	    }
	}

}