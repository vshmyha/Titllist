package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "anime_base")
public class AnimeEntity {

	@Id
	private Integer id;

	@Column(name = "rus_name")
	private String rusName;

	@Column(name = "jap_name")
	private String japName;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private TypeEntity type;

	private Integer episodes;

	private Integer duration;

	@Column(name = "release_date")
	private Short releaseDate;

	@ManyToMany
	@JoinTable(name = "anime_genre",
			joinColumns = {@JoinColumn(name = "id_anime")},
			inverseJoinColumns = {@JoinColumn(name = "id_genre")})
	private List<GenreEntity> genres;

}
