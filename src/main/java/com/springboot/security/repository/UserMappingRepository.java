package com.springboot.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.security.entity.TblUserDetails;
import com.springboot.security.entity.TblUserMapping;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserMappingRepository extends JpaRepository<TblUserMapping, Long> {

	TblUserMapping findByUserOneAndUserTwo(TblUserDetails caller, TblUserDetails reciever);
	
	

}
