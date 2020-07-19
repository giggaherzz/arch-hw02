package org.otus.repository;

import lombok.RequiredArgsConstructor;
import org.otus.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepo {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public User create(User user) {
        return jdbcTemplate.query("insert into hw02.users (name, email) values (:name, :email) returning *",
                new BeanPropertySqlParameterSource(user),
                BeanPropertyRowMapper.newInstance(User.class))
                .stream()
                .findFirst()
                .get();
    }

    public User get(int id) {
        return jdbcTemplate
                .query("select * from hw02.users where id = :id",
                        new MapSqlParameterSource("id", id),
                        BeanPropertyRowMapper.newInstance(User.class))
                .stream()
                .findFirst()
                .get();
    }

    public User update(User user) {
        return jdbcTemplate
                .query("update hw02.users set name = :name, email = :email where id = :id returning *",
                        new BeanPropertySqlParameterSource(user),
                        BeanPropertyRowMapper.newInstance(User.class))
                .stream()
                .findFirst()
                .get();
    }

    public void delete(int id) {
        jdbcTemplate
                .update("delete from hw02.users where id = :id",
                        new MapSqlParameterSource("id", id));
    }

    public List<User> getAll() {
        return jdbcTemplate
                .query("select * from hw02.users", BeanPropertyRowMapper.newInstance(User.class));
    }
}
