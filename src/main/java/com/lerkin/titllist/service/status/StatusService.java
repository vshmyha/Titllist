package com.lerkin.titllist.service.status;


import com.lerkin.titllist.dao.entity_db.StatusEntity;

import java.util.List;

public interface StatusService {

    List<StatusEntity> getStatuses();
}
