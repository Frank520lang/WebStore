package store.service;

import java.sql.SQLException;
import java.util.List;

import store.domain.Orders;
import store.domain.PageModel;
import store.domain.User;

public interface OrderService {

    void save(Orders orders) throws SQLException;

    PageModel findMyOrder(User user, int curNum) throws Exception;

    Orders findInfoByOid(String oid) throws Exception;

    List<Orders> findAllOrders() throws SQLException;

    List<Orders> findAllOrders(int st) throws SQLException;

}
