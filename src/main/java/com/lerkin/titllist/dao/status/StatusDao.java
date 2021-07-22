package com.lerkin.titllist.dao.status;

import com.lerkin.titllist.dao.entity.Status;

import java.util.List;

public interface StatusDao {

    List<Status> selectStatuses();
}
