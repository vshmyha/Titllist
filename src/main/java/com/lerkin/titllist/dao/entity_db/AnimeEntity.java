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
@Table(name = "animes")
public class AnimeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "rus_name")
	private String rusName;

	@Column(name = "jap_name")
	private String japName;

	private String type;

	private Integer episodes;

	private Integer duration;

	@Column(name = "release_date")
	private Short releaseDate;

	@ManyToMany
	@JoinTable(name = "genres_to_animes",
			joinColumns = {@JoinColumn(name = "id_anime")},
			inverseJoinColumns = {@JoinColumn(name = "id_genre")})
	private List<GenreEntity> genres;

}
