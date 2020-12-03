package store.dao;

import java.sql.SQLException;

import store.domain.User;

public interface UserDao {

    void userRegister(User user) throws SQLException;

    User findBycode(String code) throws SQLException;

    void update(User user) throws SQLException;

    User find(String username, String password) throws SQLException;

}
