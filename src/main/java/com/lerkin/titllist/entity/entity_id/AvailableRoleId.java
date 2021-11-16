package com.lerkin.titllist.entity.entity_id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRoleId implements Serializable {

	Integer roleId;
	Integer availableRoleId;
}
