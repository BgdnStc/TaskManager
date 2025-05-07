package com.github.bgdnstc.taskmanager;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> getAllUsers() {
        return jdbcClient.sql("select * from users;").query(User.class).list();
    }

    public User getUserById(Integer id) {
        return jdbcClient.sql("select * from users where id = :id;").param("id", id).query(User.class).single();
    }

    public void insertUser(User user) {
        jdbcClient.sql("insert into users(name, department) values(?,?);").params(List.of(user.name(), user.department())).update();
    }

    public void delteUser(Integer id) {
        jdbcClient.sql("delete from users where id = :id;").param("id", id).update();
    }
}
