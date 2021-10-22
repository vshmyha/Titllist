package com.lerkin.titllist.service.anime_component;

import com.lerkin.titllist.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeComponentServiceImp implements AnimeComponentService {

	private final AnimeRepository animeRepository;

	@Override
	public List<Short> getReleaseDate() {

		return animeRepository.findDistinctReleaseDates();
	}
}
