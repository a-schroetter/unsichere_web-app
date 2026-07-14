package com.example.unsichere_web_app.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.example.unsichere_web_app.models.User;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        if (user == null) return null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public User findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("password"));
            } else {
                return null;
            }
        }, id);
    }

    public User findByUsername(String sessionUsername) {
        String query = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.query(query, resultSet -> {
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            return null;
        }, sessionUsername);
    }
}
