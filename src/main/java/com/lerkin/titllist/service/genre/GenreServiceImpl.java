package com.lerkin.titllist.service.genre;

import com.lerkin.titllist.dto.GenreDto;
import com.lerkin.titllist.entity.GenreEntity;
import com.lerkin.titllist.repository.GenreRepository;
import com.lerkin.titllist.service.mapper.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;

	@Override
	public List<GenreDto> getGenres() {

		List<GenreEntity> genres = genreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		return genres.stream().map(DtoMapper::toGenreDto).collect(Collectors.toList());
	}

}
