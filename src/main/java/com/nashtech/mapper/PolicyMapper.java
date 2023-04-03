package com.nashtech.mapper;

import com.nashtech.models.Policy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicyMapper implements RowMapper<Policy> {
    public Policy mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Policy policy = new Policy();
        policy.setId(resultSet.getInt("id"));
        policy.setPolicyName(resultSet.getString("name"));
        policy.setRule(resultSet.getString("rule"));
        policy.setRuleStatus(resultSet.getString("rule_status"));
        policy.setStorageId(resultSet.getInt("storage_id"));
        policy.setPackageId(resultSet.getInt("package_id"));

        return policy;
    }
}
