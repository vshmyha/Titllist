package com.lerkin.titllist.service.anime;

import com.lerkin.titllist.dto.AnimeDto;
import com.lerkin.titllist.dto.PaginationDto;
import com.lerkin.titllist.dto.TitllistNoteDto;
import com.lerkin.titllist.dto.filter.AnimeFilter;
import com.lerkin.titllist.entity.AnimeEntity;
import com.lerkin.titllist.entity.GenreEntity;
import com.lerkin.titllist.entity.TitllistNoteEntity;
import com.lerkin.titllist.entity.UserEntity;
import com.lerkin.titllist.entity.entity_id.TitllistNoteId;
import com.lerkin.titllist.exception.RequestedObjectNotFoundException;
import com.lerkin.titllist.exception.UserFriendlyException;
import com.lerkin.titllist.repository.AnimeRepository;
import com.lerkin.titllist.repository.GenreRepository;
import com.lerkin.titllist.repository.TitllistNoteRepository;
import com.lerkin.titllist.repository.UserRepository;
import com.lerkin.titllist.service.mapper.DtoMapper;
import com.lerkin.titllist.service.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {

	private final AnimeRepository animeRepository;
	private final GenreRepository genreRepository;
	private final TitllistNoteRepository titllistNoteRepository;
	private final UserRepository userRepository;

	@Override
	public List<AnimeDto> getAnimesPage(AnimeFilter filter, PaginationDto pagination) {

		if (Objects.isNull(pagination.getPage()) || Objects.isNull(pagination.getSize())) {
			PaginationDto newPagination = new PaginationDto(0, Integer.MAX_VALUE);
			return loadByPage(filter, newPagination);
		}
		return loadByPage(filter, pagination);
	}

	private List<AnimeDto> loadByPage(AnimeFilter filter, PaginationDto pagination) {

		Integer page = pagination.getPage();
		Integer size = pagination.getSize();
		Pageable pageable = PageRequest.of(page, size);
		Page<AnimeEntity> all;
		if (Objects.nonNull(filter.getType())) {
			all = animeRepository.findByType(filter.getType(), pageable);
		} else if (Objects.nonNull(filter.getGenreId())) {
			all = animeRepository.findByGenres_Id(filter.getGenreId(), pageable);
		} else if (Objects.nonNull(filter.getReleaseDate())) {
			all = animeRepository.findByReleaseDate(filter.getReleaseDate(), pageable);
		} else {
			all = animeRepository.findAll(pageable);
		}
		return all.getContent().stream().map(DtoMapper::toAnimeDto).collect(Collectors.toList());
	}

	@Override
	public void addNewAnime(AnimeDto anime) {

		AnimeEntity entity = EntityMapper.toAnimeEntity(anime);
		List<GenreEntity> genreEntities = entity.getGenres().stream().map(genre -> genreRepository.findByName(genre.getName())).collect(Collectors.toList());
		entity.setGenres(genreEntities);
		animeRepository.save(entity);
	}

	@Override
	public AnimeDto getAnimeById(Integer animeId) {

		AnimeEntity entity = animeRepository.findById(animeId).
				orElseThrow(() -> new RequestedObjectNotFoundException("anime", animeId));
		AnimeDto anime = DtoMapper.toAnimeDto(entity);
		return anime;
	}

	@Override
	public void addAnimeToUserTitllist(Integer userId, Integer animeId, String status) {

		UserEntity userEntity = userRepository.findById(userId).
				orElseThrow(() -> new RequestedObjectNotFoundException("user", userId));
		TitllistNoteId id = new TitllistNoteId(animeId, userId);
		AnimeEntity anime = animeRepository.findById(animeId).
				orElseThrow(() -> new RequestedObjectNotFoundException("anime", animeId));

		TitllistNoteEntity entity = new TitllistNoteEntity(id, userEntity, anime, status);
		titllistNoteRepository.save(entity);
	}

	@Override
	public void checkCurrentAnimeStatus(Integer animeId, Integer userId, String status) {

		TitllistNoteEntity entity = titllistNoteRepository.findFirstByAnimeAndUser(animeId, userId);

		if (entity.getStatus().equals(status)) {
			throw new UserFriendlyException("This title has already been added to the Titllist '" + status + "'");
		}
	}

	@Override
	public void changeAnimeStatusInTitllist(Integer animeId, Integer userId, String status) {

		addAnimeToUserTitllist(userId, animeId, status);
	}

	@Override
	public List<AnimeDto> getAnimeFromUserTitllist(Integer userId) {

		List<TitllistNoteEntity> entities = titllistNoteRepository.findByUser(userId);
		List<AnimeEntity> animeEntities = entities.
				stream().map(entity -> entity.getAnime()).collect(Collectors.toList());
		return animeEntities.stream().map(DtoMapper::toAnimeDto).collect(Collectors.toList());
	}

	@Override
	public List<AnimeDto> getAnimeFromUserTitllistByStatus(Integer userId, String status) {

		List<TitllistNoteEntity> entities = titllistNoteRepository.findByUserAndStatus(userId, status);
		List<AnimeEntity> animeEntities = entities.
				stream().map(entity -> entity.getAnime()).collect(Collectors.toList());
		return animeEntities.stream().map(DtoMapper::toAnimeDto).collect(Collectors.toList());
	}

	@Override
	public List<AnimeDto> searchAnimesByName(String animeName) {

		List<AnimeEntity> entities = animeRepository.findByRusNameContainsOrJapNameContains(animeName, animeName);
		if (Objects.nonNull(entities)) {
			return entities.stream().map(DtoMapper::toAnimeDto).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public TitllistNoteDto getTitllistNote(Integer animeId, Integer userId) {

		TitllistNoteEntity note = titllistNoteRepository.findFirstByAnimeAndUser(animeId, userId);

		return DtoMapper.toTitllistNoteDto(note);
	}
}
