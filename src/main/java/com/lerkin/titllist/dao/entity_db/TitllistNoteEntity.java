package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "titllist_notes")
public class TitllistNoteEntity implements Serializable {

	@ManyToOne
	@JoinColumn(name = "user_id")
	@Id
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "anime_id")
	@Id
	private AnimeEntity anime;

	private String status;

}
