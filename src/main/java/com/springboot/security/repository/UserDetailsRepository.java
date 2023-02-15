package com.springboot.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.security.entity.TblUserDetails;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<TblUserDetails, Long>{
	
	public TblUserDetails findByUserName(String name);
	
	public TblUserDetails findByUserNameAndPassword(String name,String password);

	@Modifying(clearAutomatically = true)
	@Query(value = "ALTER TABLE tbl_user_details AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
	
	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.email like concat( '%', ?1, '%') ", nativeQuery = true)
	public List<Tuple> findAllByEmail(String email);
	
	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where td.phone like concat( '%', ?1, '%') ", nativeQuery = true)
	public List<Tuple> findAllByPhone(String phone);
	
	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.user_name like concat( '%', ?1, '%') ", nativeQuery = true)
	public List<Tuple> findAllByUserName(String userName);
	
	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.address like concat( '%', ?1, '%') ", nativeQuery = true)
	public List<Tuple> findAllByAddress(String address);
	
	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.email like concat( '%', ?1, '%') and td.phone like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByEmailAndPhone(String email, String phone);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where td.phone like concat( '%', ?1, '%') and tud.email like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByPhoneEmail(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.email like concat( '%', ?1, '%') and tud.user_name like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByEmailUserName(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.user_name like concat( '%', ?1, '%') and tud.email like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByUserNameEmail(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.email like concat( '%', ?1, '%') and tud.address like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByEmailAddress(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.address like concat( '%', ?1, '%') and tud.email like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByAddressEmail(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where td.phone like concat( '%', ?1, '%') and tud.user_name like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByPhoneUserName(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where td.phone like concat( '%', ?1, '%') and tud.address like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByPhoneAddress(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.user_name like concat( '%', ?1, '%') and td.phone like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByUserNamePhone(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.address like concat( '%', ?1, '%') and td.phone like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByAddressPhone(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.user_name like concat( '%', ?1, '%') and tud.address like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByUserNameAddress(String string, String string2);

	@Query(value = "select tud.id, tudr.roles, tud.address, tud.call_charge, tud.created_at, tud.dob, tud.email, "
			+ "tud.password, tud.updated_at, tud.user_name, tud.device_id,  td.created_at as device_created_at, "
			+ "td.imei_number, td.phone, td.updated_at as device_updated_at "
			+ "from tbl_user_details tud join "
			+ "tbl_device td on tud.device_id = td.id "
			+ "join tbl_user_details_roles tudr on tud.id = tudr.tbl_user_details_id "
			+ "where tud.address like concat( '%', ?1, '%') and tud.user_name like concat( '%', ?2, '%') ", nativeQuery = true)
	public List<Tuple> findAllByAddressUserName(String string, String string2);
	
	
	
	
	
	
	
	
	
}
