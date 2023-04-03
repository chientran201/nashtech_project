package com.nashtech.mapper;

import com.nashtech.models.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageMapper implements RowMapper<Storage> {
    public Storage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Storage storage = new Storage();
        storage.setId(resultSet.getInt("id"));
        storage.setNameStorage(resultSet.getString("name"));
        storage.setHeight(resultSet.getDouble("height"));
        storage.setWidth(resultSet.getDouble("width"));
        storage.setLength(resultSet.getDouble("length"));
        storage.setAddressStorage(resultSet.getString("address"));

        return storage;
    }
}
