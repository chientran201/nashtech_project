package com.nashtech.dao;

import com.nashtech.mapper.PackageMapper;
import com.nashtech.models.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class PackageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Package Packages) {
        String sql = "INSERT INTO packages (length, width, height, sender, sender_email, cell_id, customer_id, created_at, modified_at, created_by, modified_by, status, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, Packages.getLength(), Packages.getWidth(), Packages.getHeight(), Packages.getSender(), Packages.getSenderEmail(), Packages.getCellId(), Packages.getCustomerId(), Packages.getCreateAt(), Packages.getModifiedAt(), Packages.getCreateBy(), Packages.getModifiedBy(), Packages.getStatus(), Packages.getType());
    }

    public Package findById(int id) {
        String sql = "SELECT * FROM packages WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PackageMapper(), id);
    }

    public List<Package> findAll() {
        return jdbcTemplate.query("SELECT * FROM packages", new RowMapper<Package>() {
            @Override
            public Package mapRow(ResultSet rs, int i) throws SQLException {
                Package packages = new Package();
                packages.setId(rs.getInt(1));
                packages.setSender(rs.getString(5));
                packages.setSenderEmail(rs.getString(6));
                packages.setCellId(rs.getInt(7));
                packages.setStatus(rs.getString(13));
                packages.setType(rs.getString(14));
                return packages;
            }
        });

    }

    public List<Package> findPackage(int id, String email) {
        List<Package> listPackage = new ArrayList<Package>();
        String sql = "SELECT * FROM packages pk INNER JOIN customers ct ON pk.customer_id = ct.id WHERE pk.id =" + id + " AND ct.email = '" + email + "'";
        listPackage = jdbcTemplate.query(sql, new RowMapper<Package>() {
            @Override
            public Package mapRow(ResultSet rs, int rowNum) throws SQLException {
                Package packages = new Package();
                packages.setId(rs.getInt("id"));
                packages.setSender(rs.getString("sender"));
                packages.setSenderEmail(rs.getString("sender_email"));
                packages.setStatus(rs.getString("status"));
                return packages;
            }
        });
        return listPackage;
    }

    public void deliver(int id) {
        String sql = "DELETE FROM packages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
