package com.nashtech.mapper;

import com.nashtech.models.Cabinet;
import com.nashtech.models.Cell;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CellMapper implements RowMapper<Cell> {
    public Cell mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Cell cell = new Cell();
        cell.setId(resultSet.getInt("id"));
        cell.setHeight(resultSet.getDouble("height"));
        cell.setWidth(resultSet.getDouble("width"));
        cell.setLength(resultSet.getDouble("length"));
        cell.setRow(resultSet.getInt("row"));
        cell.setColumn(resultSet.getInt("column"));
        cell.setCabinetId(resultSet.getInt("storage_id"));
        cell.setAvailable(resultSet.getInt("availble"));

        return cell;
    }
}
