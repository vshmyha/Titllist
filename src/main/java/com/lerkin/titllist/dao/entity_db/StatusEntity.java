package com.lerkin.titllist.dao.entity_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "statuses")
public class StatusEntity {

    @Id
    private Integer id;

    private String name;


}
