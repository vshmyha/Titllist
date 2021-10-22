package com.lerkin.titllist.dao.entity_db;

import com.lerkin.titllist.dao.entity_db.entity_id.TitllistNoteId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "titllist_notes")
public class TitllistNoteEntity implements Serializable {

	@EmbeddedId
	private TitllistNoteId id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId("userId")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "anime_id")
	@MapsId("animeId")
	private AnimeEntity anime;

	private String status;

}
