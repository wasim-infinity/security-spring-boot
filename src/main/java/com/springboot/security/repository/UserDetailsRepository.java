package com.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.security.entity.TblUserDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<TblUserDetails, Long>{
	
	public TblUserDetails findByUserName(String name);
	
	public TblUserDetails findByUserNameAndPassword(String name,String password);

	@Modifying(clearAutomatically = true)
	@Query(value = "ALTER TABLE tbl_user_details AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
}
