package com.lerkin.titllist.service.status;

import com.lerkin.titllist.dao.DaoFactory;
import com.lerkin.titllist.dao.entity.Status;
import com.lerkin.titllist.dao.status.StatusDao;

import java.util.List;

public class StatusServiceImpl implements StatusService {

    private final StatusDao statusDao = DaoFactory.getStatusDao();

    @Override
    public List<Status> getStatuses() {
        return statusDao.selectStatuses();
    }
}
