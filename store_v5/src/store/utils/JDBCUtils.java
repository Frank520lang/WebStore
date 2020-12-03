package store.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
    // c3p0中的一个类,ComboPooledDataSource()为创建数据源的一个构造方法
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    // 这个类提供线程局部变量
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 从线程中获取连接
     * 
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 从线程中获取conneciton
        // 判断当前线程中有没有连接对象
        Connection conn = tl.get();// 获取到当前线程变量的一个副本
        if (conn == null) {
            // 如果没有连接对象就创建连接对象
            conn = ds.getConnection();
            // 和当前线程绑定
            tl.set(conn);
        }
        return conn;
    }

    // 获取数据源
    public static DataSource getDataSource() {
        return ds;
    }

    // 释放资源
    public static void closeResource(Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
    }

    // 释放资源
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        closeResource(st, rs);
        closeConn(conn);
    }

    // 释放 connection
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                // 和线程解绑
                tl.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    // 释放 statement ctrl + shift + f 格式化代码
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            st = null;
        }
    }

    // 释放结果集
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }

    // 开启事务
    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     * 事务提交且释放连接
     */
    public static void commitAndClose() {
        Connection conn = null;
        try {
            conn = getConnection();
            // 事务提交
            conn.commit();
            // 关闭资源
            conn.close();
            // 解除版定
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务回滚且释放资源
     */
    public static void rollbackAndClose() {
        Connection conn = null;
        try {
            conn = getConnection();
            // 事务回滚
            conn.rollback();
            // 关闭资源
            conn.close();
            // 解除版定
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 验证数据库是否正常连接
    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}
