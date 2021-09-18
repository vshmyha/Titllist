package com.lerkin.titllist.service.release_date;

import com.lerkin.titllist.dao.release_date.ReleaseDateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReleaseDateServiceImp implements ReleaseDateService {

	private final ReleaseDateDao releaseDateDao;

	@Override
	public List<Short> getReleaseDate() {

		return releaseDateDao.selectReleaseDates();
	}
}
