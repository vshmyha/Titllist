package com.lerkin.titllist.service.genre;

import com.lerkin.titllist.dao.entity_db.GenreEntity;
import com.lerkin.titllist.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;

	@Override
	public List<GenreEntity> getGenres() {

		return genreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

}
