package com.Project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cities {
	
	@Id
	private Integer cid;
	private String cities;
	private Integer sid;
	
	
	
	public Cities() {
		super();
	}
	public Cities(Integer cid, String cities, Integer sid) {
		super();
		this.cid = cid;
		this.cities = cities;
		this.sid = sid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCities() {
		return cities;
	}
	public void setCities(String cities) {
		this.cities = cities;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	

}
