package com.nashtech.dao;

import com.nashtech.models.Cabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CabinetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cabinet> getCabinets() {
        return jdbcTemplate.query("SELECT id, name, height, width, length, storage_id FROM cabinets", new RowMapper<Cabinet>() {
            @Override
            public Cabinet mapRow(ResultSet rs, int row) throws SQLException {
                Cabinet cabinet = new Cabinet();
                cabinet.setId(rs.getInt(1));
                cabinet.setCabinetName(rs.getString(2));
                cabinet.setHeight(rs.getDouble(3));
                cabinet.setWidth(rs.getDouble(4));
                cabinet.setLength(rs.getDouble(5));
                cabinet.setStorageId(rs.getInt(6));
                return cabinet;
            }
        });
    }

    public void create(Cabinet cabinet) {
        String sql = "INSERT INTO cabinets (name, height, width, length, storage_id) VALUES (?, ? ,? ,?, ?)";
        jdbcTemplate.update(sql, cabinet.getCabinetName(), cabinet.getHeight(), cabinet.getWidth(), cabinet.getLength(), cabinet.getStorageId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM cabinets WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
