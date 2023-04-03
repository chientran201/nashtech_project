package com.nashtech.mapper;

import com.nashtech.models.Cabinet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CabinetMapper implements RowMapper<Cabinet> {
    public Cabinet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Cabinet cabinet = new Cabinet();
        cabinet.setId(resultSet.getInt("id"));
        cabinet.setCabinetName(resultSet.getString("name"));
        cabinet.setHeight(resultSet.getDouble("height"));
        cabinet.setWidth(resultSet.getDouble("width"));
        cabinet.setLength(resultSet.getDouble("length"));
        cabinet.setStorageId(resultSet.getInt("storage_id"));
        return cabinet;
    }
}
