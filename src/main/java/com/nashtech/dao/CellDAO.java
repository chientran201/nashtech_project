package com.nashtech.dao;


import com.nashtech.models.Cell;
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
public class CellDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Cell> findAll() {
        return jdbcTemplate.query("SELECT * FROM cells", new RowMapper<Cell>() {
            @Override
            public Cell mapRow(ResultSet rs, int i) throws SQLException {
                Cell cell = new Cell();
                cell.setId(rs.getInt(1));
                cell.setHeight(rs.getDouble(2));
                cell.setWidth(rs.getDouble(3));
                cell.setLength(rs.getDouble(4));
                cell.setRow(rs.getInt(5));
                cell.setColumn(rs.getInt(6));
                cell.setCabinetId(rs.getInt(7));
                cell.setAvailable(rs.getInt(8));
                return cell;
            }
        });

    }

    public void createCell(Cell cells) {
        String sql = "INSERT INTO cells(height, width, length, `row`, `column`, cabinet_id, availble) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, cells.getHeight(), cells.getWidth(), cells.getLength(), cells.getRow(),
                cells.getColumn(), cells.getCabinetId(), cells.getAvailable());
    }

    public void deleteCell(int id) {
        String sql = "DELETE FROM cells WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

