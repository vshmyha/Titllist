package com.lerkin.titllist.dao.release_date;

import com.lerkin.titllist.dao.config.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class ReleaseDateDaoImpl implements ReleaseDateDao {

    private static final String SELECT_RELEASE_DATE_ORDER = "SELECT DISTINCT release_date FROM anime_base ORDER BY release_date";

    @Override
    public List<Short> selectReleaseDates() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Short> releaseDate = new ArrayList<>();
        try {
            connection = ConnectionManager.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_RELEASE_DATE_ORDER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Short date = resultSet.getShort(1);
                releaseDate.add(date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionManager.close(connection, preparedStatement, resultSet);
        }
        return releaseDate;
    }
}
