package com.Project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Entity.Cities;

@Repository
public interface Citiesrepo extends JpaRepository<Cities, Integer> {

}
