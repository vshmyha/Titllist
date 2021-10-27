package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
