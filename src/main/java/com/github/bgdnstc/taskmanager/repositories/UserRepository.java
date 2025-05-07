package com.github.bgdnstc.taskmanager.repositories;

import com.github.bgdnstc.taskmanager.records.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // query all users and list them
    public List<User> getAllUsers() {
        return jdbcClient.sql("select * from users;").query(User.class).list();
    }

    // query one user by id
    public User getUserById(Integer id) {
        return jdbcClient.sql("select * from users where id = :id;").param("id", id).query(User.class).single();
    }

    // insert a user in the users table
    public void insertUser(User user) {
        jdbcClient.sql("insert into users(name, department) values(?,?);").params(List.of(user.name(), user.department())).update();
    }

    // remove one record from the database by their id
    public void deleteUser(Integer id) {
        jdbcClient.sql("delete from users where id = :id;").param("id", id).update();
    }
}
