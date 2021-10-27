package com.lerkin.titllist.dao.entity_db.entity_id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class TitllistNoteId implements Serializable {

	private Integer animeId;
	private Integer userId;

}
