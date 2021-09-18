package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "types")
public class TypeEntity {

	@Id
	private Integer id;

	private String name;

	//    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
	//    private List<AnimeEntity> animes;

}
