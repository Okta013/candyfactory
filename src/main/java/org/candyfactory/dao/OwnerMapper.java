package org.candyfactory.dao;
import org.candyfactory.model.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerMapper implements RowMapper<Owner>{

    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        owner.setSurname(rs.getString("surname"));
        owner.setPhone(rs.getString("phone"));
        owner.setEmail(rs.getString("email"));
        return owner;
    }
}
