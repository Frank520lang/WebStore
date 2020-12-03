package store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import store.domain.OrderItem;
import store.domain.Orders;
import store.domain.User;

public interface OrderDao {

    void saveOrder(Connection cnn, Orders orders) throws SQLException;

    void saveOrderItem(Connection cnn, OrderItem orderItem) throws SQLException;

    int findTotalRecords(User user) throws SQLException;

    List<Orders> findMyOrderByUid(User user, int startIndex, int pageSize) throws Exception;

    Orders findInfoByOid(String oid) throws Exception;

    List<Orders> findAllOrders() throws SQLException;

    List<Orders> findAllOrders(int st) throws SQLException;

}
