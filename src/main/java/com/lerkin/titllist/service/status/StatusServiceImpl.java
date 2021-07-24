package com.lerkin.titllist.service.status;

import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.status.StatusDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StatusServiceImpl implements StatusService {

    private final StatusDao statusDao;

    @Override
    public List<Status> getStatuses() {
        return statusDao.selectStatuses();
    }
}
