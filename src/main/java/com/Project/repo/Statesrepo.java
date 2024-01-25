package com.Project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Entity.States;

@Repository
public interface Statesrepo extends JpaRepository<States,Integer> {

}
