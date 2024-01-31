package com.Project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Entity.Countries;

@Repository
public interface Countriesrepo extends JpaRepository<Countries,Integer> {

	
}
