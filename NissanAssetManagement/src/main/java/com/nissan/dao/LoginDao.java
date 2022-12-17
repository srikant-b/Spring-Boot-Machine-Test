package com.nissan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nissan.entity.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, Integer> {

	Login findOneByUsernameAndPassword(String userName, String password);
	
}
