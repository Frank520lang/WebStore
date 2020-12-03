package store.service;

import java.sql.SQLException;

import store.domain.User;

public interface UserService {
    void userRegister(User user) throws SQLException;

    void activeUser(String code) throws SQLException;

    User login(User user) throws SQLException;
}
