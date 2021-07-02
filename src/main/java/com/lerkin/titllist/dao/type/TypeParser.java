package com.lerkin.titllist.dao.type;

import com.lerkin.titllist.entity_db.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class TypeParser {

    public static List<Type> parseListTypes(ResultSet resultSet) throws SQLException {
        List<Type> types = new ArrayList<>();
        while (resultSet.next()) {
            String typeName = resultSet.getString("type_name");
            Integer id = resultSet.getInt("id_type");
            Type type = new Type(typeName);
            type.setId(id);
            types.add(type);
        }
        return types;
    }

    public static Type parse(ResultSet resultSet) throws SQLException {
        String typeName = resultSet.getString("type_name");
        Integer id = resultSet.getInt("id_type");
        Type type = new Type(typeName);
        type.setId(id);
        return type;
    }
}
