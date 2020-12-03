package store.dao.daoImp;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import store.dao.UserDao;
import store.domain.User;
import store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {
    public void userRegister(User user) throws SQLException {
        // TODO 自动生成的方法存根
        // QueryRunner需要一个数据源作为参数
        String sql =
            "insert into user(uid,username,password,name,email,telephone,birthday,sex,state,code) values(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
            user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
        queryRunner.update(sql, params);
    }

    public User findBycode(String code) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where code = ?";
        // 对比传进来的code和数据库中的code是否一致
        User exitUser = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
        return exitUser;
    }

    public void update(User user) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql =
            "update user set uid=?,username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=?";
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
            user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
        queryRunner.update(sql, params);
    }

    @Override
    public User find(String username, String password) throws SQLException {
        // TODO 自动生成的方法存根
        // 获取数据源
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ?";
        return queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
    }

}
