package com.Project.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Countries {
	
	@Id
	private Integer crid;
	private String countries;
	
	
	
	public Countries() {
		
	}
	public Countries(Integer crid, String countries) {
		super();
		this.crid = crid;
		this.countries = countries;
	}
	public Integer getCrid() {
		return crid;
	}
	public void setCrid(Integer crid) {
		this.crid = crid;
	}
	public String getCountries() {
		return countries;
	}
	public void setCountries(String countries) {
		this.countries = countries;
	}
	

}
