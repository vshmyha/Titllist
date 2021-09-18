package com.lerkin.titllist.service.status;

import com.lerkin.titllist.dao.entity_db.StatusEntity;
import com.lerkin.titllist.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StatusServiceImpl implements StatusService {

	private final StatusRepository statusRepository;

	@Override
	public List<StatusEntity> getStatuses() {

		return statusRepository.findAll();
	}
}
