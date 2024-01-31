package com.Project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class States {
	
	@Id
	private Integer sid;
	private String states;
	private Integer crid;
	
	
	public States() {
		super();
	}
	
	public States(Integer sid, String states, Integer crid) {
		super();
		this.sid = sid;
		this.states = states;
		this.crid = crid;
	}

	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public Integer getCrid() {
		return crid;
	}
	public void setCrid(Integer crid) {
		this.crid = crid;
	}
	

}
