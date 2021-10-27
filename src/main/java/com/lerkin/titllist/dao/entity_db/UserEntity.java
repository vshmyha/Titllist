package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username")
	private String userName;

	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	public UserEntity(String userName, String password, RoleEntity role) {

		this.userName = userName;
		this.password = password;
		this.role = role;
	}

}
