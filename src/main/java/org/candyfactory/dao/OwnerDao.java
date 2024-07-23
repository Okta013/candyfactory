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
        return jdbcTemplate.query("SELECT * FROM owner", new BeanPropertyRowMapper<>(Owner.class));
    }

    public Owner show(int id){
        return jdbcTemplate.query("SELECT * FROM owner WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Owner.class)).stream().findAny().orElse(null);
    }

    public void save(Owner owner){
        jdbcTemplate.update("INSERT INTO owner (name, surname, phone, email) VALUES (?, ?, ?, ?)",
                owner.getName(), owner.getSurname(), owner.getPhone(), owner.getEmail());
    }

    public void update(int id, Owner updatedOwner){
        jdbcTemplate.update("UPDATE owner SET name=?, surname=?, phone=?, email=? WHERE id=?",
                updatedOwner.getName(), updatedOwner.getSurname(), updatedOwner.getPhone(), updatedOwner.getEmail(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM owner WHERE id=?", id);
    }

}
