package com.springboot.security.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_mapping")
@Entity
public class TblUserMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_one", nullable = true, columnDefinition = " INT(11) COMMENT 'Primary id of au_account_variant'")
	private TblUserDetails userOne;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_two", nullable = true, columnDefinition = " INT(11) COMMENT 'Primary id of au_account_benefits'")
	private TblUserDetails userTwo;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
