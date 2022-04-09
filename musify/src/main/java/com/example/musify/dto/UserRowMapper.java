package com.example.musify.dto;

import com.example.musify.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user;
        user = new User(rs.getInt("id"), rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("password"),
                rs.getString("country_of_origin"),rs.getInt("role"));
        return user;
    }
}
