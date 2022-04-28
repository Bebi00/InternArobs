package com.example.musify.repo;

import com.example.musify.dto.*;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.User;
import com.example.musify.security.JWTUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Component
public class UserRepo implements DAO<User> {
    private final JdbcTemplate jdbcTemplate;
    private final JWTUtils jwtUtils;

    public UserRepo(DataSource dataSource, JWTUtils jwtUtils) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Optional<User> getById(Long id) {
        User user = null;
        String sql = "SELECT * from musify.users WHERE id="+id;
        List<User> users = jdbcTemplate.query(sql,new UserRowMapper());
        if (users.size() == 1){
            user = users.get(0);
        }
        assert user != null;
        return Optional.of(user);
    }
    public Optional<User> getByEmail(String email) {
        User user = null;
        String sql = String.format("SELECT * from musify.users WHERE email='%s'",email);

        List<User> users = jdbcTemplate.query(sql,new UserRowMapper());
        if (users.size() == 1){
            user = users.get(0);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        users= jdbcTemplate.query("SELECT * from users",new UserRowMapper());
        return users;
    }

    @Override

    public User save(User user) {
        String sql = String.format("INSERT INTO musify.users (first_name,last_name,email,password,country_of_origin,role,active) " +
                        "VALUES ('%s','%s','%s','%s','%s',%d,%d)"
                ,user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getCountryOfOrigin(),0,1);
        jdbcTemplate.update(sql);
        return user;
    }

    public void update(User user) {
        String sql = String.format("UPDATE musify.users SET first_name='%s',last_name='%s',email='%s',password='%s',country_of_origin ='%s'" +
                " WHERE id='%d'",user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getCountryOfOrigin(),jwtUtils.getUserId());
        jdbcTemplate.update(sql);
    }

    public User setRole(Long userId,int role){
        String sql = String.format("UPDATE musify.users SET role = '%d' WHERE id='%d'",role,userId);
        jdbcTemplate.update(sql);
        sql = String.format("SELECT * from musify.users WHERE id='%d'",userId);
        return jdbcTemplate.query(sql,new UserRowMapper()).get(0);
    }

    @Override
    public void delete(User user) {
    }

    public Long getId(UserDTO userDTO){
        String sql = String.format("SELECT * from musify.users WHERE email='%s'",userDTO.getEmail());
        User user = jdbcTemplate.query(sql,new UserRowMapper()).get(0);
        return user.getId();
    }

    public String removeToken(String token){
        String sql = String.format("DELETE from musify.tokens WHERE token='%s'",token);
        jdbcTemplate.update(sql);
        return token;
    }

    public String addToken(String token,Long id, Date expiryDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(expiryDate);
        String sql = String.format("INSERT INTO musify.tokens (user_id,token,expiry_date) VALUES ('%d','%s','%s')",id,token,date);
        jdbcTemplate.update(sql);
        sql = "DELETE FROM `musify`.`tokens` WHERE expiry_date <= NOW()";
        jdbcTemplate.update(sql);
        return token;
    }

    public boolean checkToken(String token){
        String sql = String.format("SELECT DISTINCT token FROM `musify`.`tokens` WHERE token = '%s' ",token);
        List<String> res = jdbcTemplate.query(sql,new TokenRowMapper());
        String dbToken = null;
        if(res.size() == 1){
            dbToken = res.get(0);
        }
        return token.equals(dbToken);

    }

    public User inactivateUser(Long id){
        String sql = String.format("UPDATE musify.users SET active = '%d' WHERE id='%d'",0,id);
        jdbcTemplate.update(sql);
        sql = String.format("SELECT * from musify.users WHERE id='%d'",id);
        return jdbcTemplate.query(sql,new UserRowMapper()).get(0);
    }

    public List<Playlist> getPlaylists(Long userId){
        String sql = String.format("SELECT * from musify.users_playlists UP JOIN musify.playlists P ON UP.playlist_id = P.id WHERE user_id='%d'",userId);
        return jdbcTemplate.query(sql,new PlaylistRowMapper());
    }
}
