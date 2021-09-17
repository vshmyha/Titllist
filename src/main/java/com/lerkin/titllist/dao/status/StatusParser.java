package com.lerkin.titllist.dao.status;

import com.lerkin.titllist.dao.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusParser {

    public static List<Status> listParser(ResultSet resultSet) throws SQLException {
        List<Status> statuses = new ArrayList<>();
        while (resultSet.next()) {
            String statusName = resultSet.getString("name");
            Status status = Status.byText(statusName);
            statuses.add(status);
        }
        return statuses;
    }
}

