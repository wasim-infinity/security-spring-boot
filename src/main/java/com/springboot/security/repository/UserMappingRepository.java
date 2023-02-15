package com.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.security.entity.TblUserMapping;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserMappingRepository extends JpaRepository<TblUserMapping, Long> {
	
	

}
