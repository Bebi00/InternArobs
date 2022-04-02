package com.example.musify.repo;

import com.example.musify.dto.DAO;
import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserRowMapper;
import com.example.musify.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo implements DAO<User> {
    private final JdbcTemplate jdbcTemplate;

    public UserRepo(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> getById(int id) {
        User user;
        String sql = "SELECT * from users WHERE id="+id;
        user = jdbcTemplate.query(sql,new UserRowMapper()).get(0);
        return Optional.of(user);
    }
    public Optional<User> getByEmail(String email) {
        User user;
        String sql = String.format("SELECT * from musify.users WHERE email='%s'",email);

        user = jdbcTemplate.query(sql,new UserRowMapper()).get(0);
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        users= jdbcTemplate.query("SELECT * from users",new UserRowMapper());
        return users;
    }

    @Override
    public User save(User user) {
        String sql = String.format("INSERT INTO musify.users (first_name,last_name,email,password,country_of_origin,role) VALUES ('%s','%s','%s','%s','%s',%d)",user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getCountryOfOrigin(),0);
        jdbcTemplate.update(sql);
        return user;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    public Integer getId(UserDTO userDTO){
        String sql = String.format("SELECT * from musify.users WHERE email='%s'",userDTO.getEmail());
        User user = jdbcTemplate.query(sql,new UserRowMapper()).get(0);
        return user.getId();
    }

    public String removeToken(String token){
        String sql = String.format("DELETE from musify.tokens WHERE token='%s'",token);
        jdbcTemplate.update(sql);
        return token;
    }

    public String addToken(String token,int id, Date expiryDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(expiryDate);
        String sql = String.format("INSERT INTO musify.tokens (user_id,token,expiry_date) VALUES ('%d','%s','%s')",id,token,date);
        jdbcTemplate.update(sql);
        sql = "DELETE FROM `musify`.`tokens` WHERE expiry_date <= NOW()";
        jdbcTemplate.update(sql);
        return token;
    }
}
