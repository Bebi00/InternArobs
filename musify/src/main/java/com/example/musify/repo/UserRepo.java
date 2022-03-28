package com.example.musify.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo implements DAO<User> {
    private final JdbcTemplate jdbcTemplate;

    public UserRepo(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> get(int id) {
        User user;
        String sql = "SELECT * from users WHERE id="+id;
        user = jdbcTemplate.query(sql,new UserMapper()).get(0);
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        users= jdbcTemplate.query("SELECT * from users",new UserMapper());
        return users;
    }

    @Override
    public int save(User user) {
        jdbcTemplate.update("INSERT INTO users (firstName,lastName) VALUES (?,?)",user.getFirstName(),user.getLastName());
        return 0;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
