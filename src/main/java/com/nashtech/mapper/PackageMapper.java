package com.nashtech.mapper;

import com.nashtech.models.Package;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageMapper implements RowMapper<Package> {
    public Package mapRow(ResultSet rs, int rowNum) throws SQLException {
        Package packages = new Package();
        packages.setId(rs.getInt("id"));
        packages.setLength(rs.getDouble("length"));
        packages.setWidth(rs.getDouble("width"));
        packages.setHeight(rs.getDouble("height"));
        packages.setSender(rs.getString("sender"));
        packages.setSenderEmail(rs.getString("sender_email"));
        packages.setCellId(rs.getInt("cell_id"));
        packages.setCustomerId(rs.getInt("customer_id"));
//        packages.setCustomerEmail(rs.getString("customer_email"));
        packages.setCreateAt(rs.getString("created_at"));
        packages.setModifiedAt(rs.getString("modified_at"));
        packages.setCreateBy(rs.getString("created_by"));
        packages.setModifiedBy(rs.getString("modified_by"));
        packages.setStatus(rs.getString("status"));
        packages.setType(rs.getString("type"));
        return packages;
    }
}
