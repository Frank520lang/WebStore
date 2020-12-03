package store.service.serviceImp;

import java.sql.SQLException;

import store.dao.UserDao;
import store.dao.daoImp.UserDaoImp;
import store.domain.User;
import store.service.UserService;

public class UserServiceImp implements UserService {

    @Override
    public void userRegister(User user) throws SQLException {
        // TODO 自动生成的方法存根
        UserDao userDao = new UserDaoImp();
        userDao.userRegister(user);

    }

    @Override
    public void activeUser(String code) throws SQLException {

        // TODO 自动生成的方法存根
        // 通过激活码验证用户
        UserDao userDao = new UserDaoImp();
        User exitUser = userDao.findBycode(code);
        if (exitUser == null) {
            throw new RuntimeException("用户激活失败,请重试,或者从新发送邮件");
        }
        exitUser.setState(1);
        exitUser.setCode(null);
        userDao.update(exitUser);

    }

    @Override
    public User login(User user) throws SQLException {
        // TODO 自动生成的方法存根
        UserDao userDao = new UserDaoImp();

        return userDao.find(user.getUsername(), user.getPassword());
    }

}
