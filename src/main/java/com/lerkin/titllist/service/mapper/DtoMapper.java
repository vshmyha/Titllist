package com.lerkin.titllist.service.mapper;

import com.lerkin.titllist.dao.entity_db.AnimeEntity;
import com.lerkin.titllist.dao.entity_db.AvailableRoleEntity;
import com.lerkin.titllist.dao.entity_db.GenreEntity;
import com.lerkin.titllist.dao.entity_db.RoleEntity;
import com.lerkin.titllist.dto.Role;
import com.lerkin.titllist.dto.Status;
import com.lerkin.titllist.dao.entity_db.TitllistNoteEntity;
import com.lerkin.titllist.dto.Type;
import com.lerkin.titllist.dao.entity_db.UserEntity;
import com.lerkin.titllist.dto.AnimeDto;
import com.lerkin.titllist.dto.GenreDto;
import com.lerkin.titllist.dto.TitllistNoteDto;
import com.lerkin.titllist.dto.UserDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface DtoMapper {

	static AnimeDto toAnimeDto(AnimeEntity entity) {

		if (Objects.nonNull(entity)) {
			Type type = Type.byText(entity.getType());
			List<GenreDto> genres = entity.getGenres().stream().map(DtoMapper::toGenreDto).collect(Collectors.toList());
			return new AnimeDto(entity.getId(), entity.getRusName(), entity.getJapName(), type, entity.getEpisodes(), entity.getDuration(), entity.getReleaseDate(), genres);
		}
		return null;
	}

	static TitllistNoteDto toTitllistNoteDto(TitllistNoteEntity entity) {

		if (Objects.nonNull(entity)) {
			AnimeDto anime = toAnimeDto(entity.getAnime());
			UserDto user = toUserDto(entity.getUser());
			Status status = Status.byText(entity.getStatus());
			return new TitllistNoteDto(anime, status, user);
		}
		return null;
	}

	static GenreDto toGenreDto(GenreEntity entity) {

		if (Objects.nonNull(entity)) {
			return new GenreDto(entity.getId(), entity.getName());
		}
		return null;
	}

	static UserDto toUserDto(UserEntity entity) {

		if (Objects.nonNull(entity)) {
			String roleName = entity.getRole().getName();
			Role role = Role.byText(roleName);
			return new UserDto(entity.getId(), entity.getUserName(), entity.getPassword(), role);
		}
		return null;
	}

	static Role toRole(AvailableRoleEntity entity) {

		if(Objects.nonNull(entity)) {
			String roleName = entity.getAvailableRole().getName();
			Role role = Role.byText(roleName);
			return role;
		}
		return null;
	}

}
