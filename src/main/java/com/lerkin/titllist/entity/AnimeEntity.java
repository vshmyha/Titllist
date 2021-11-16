package com.lerkin.titllist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "animes")
@Builder
public class AnimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ToString.Exclude
	private List<GenreEntity> genres;

	public AnimeEntity(String rusName, String japName, String type, Integer episodes, Integer duration, Short releaseDate,
			List<GenreEntity> genres) {

		this.rusName = rusName;
		this.japName = japName;
		this.type = type;
		this.episodes = episodes;
		this.duration = duration;
		this.releaseDate = releaseDate;
		this.genres = genres;
	}
}