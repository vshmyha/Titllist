package com.lerkin.titllist.entity;

import com.lerkin.titllist.entity.entity_id.AvailableRoleId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "available_roles")
public class AvailableRoleEntity implements Serializable {

	@EmbeddedId
	private AvailableRoleId id;

	@OneToOne
	@JoinColumn(name = "role_id")
	@MapsId("roleId")
	private RoleEntity role;

	@OneToOne
	@JoinColumn(name = "available_role_id")
	@MapsId("availableRoleId")
	private RoleEntity availableRole;

}