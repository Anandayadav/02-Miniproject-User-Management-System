package com.Project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Entity.User;

@Repository
public interface Userrepo extends JpaRepository<User, Integer> {
	
	
	public User findByEmailAndPassword(String email,String password);
	
	
}
