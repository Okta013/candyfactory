package org.candyfactory.dao;

import org.candyfactory.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OwnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Owner> index() {
        return jdbcTemplate.query("SELECT * FROM \"Owner\"", new BeanPropertyRowMapper<>(Owner.class));
    }

    public Owner show(int id){
        return jdbcTemplate.query("SELECT * FROM \"Owner\" WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Owner.class)).stream().findAny().orElse(null);
    }

}
