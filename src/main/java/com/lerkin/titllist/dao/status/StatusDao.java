package com.lerkin.titllist.dao.status;

import com.lerkin.titllist.entity_db.Status;

import java.util.List;

public interface StatusDao {

    List<Status> selectStatuses();
}
