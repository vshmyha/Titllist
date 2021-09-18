package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Data
public class RoleEntity {

	@Id
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<UserEntity> users;

}
