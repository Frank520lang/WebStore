package store.service.serviceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import store.dao.OrderDao;
import store.dao.daoImp.OrderDaoImp;
import store.domain.OrderItem;
import store.domain.Orders;
import store.domain.PageModel;
import store.domain.User;
import store.service.OrderService;
import store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

    @Override
    public void save(Orders orders) throws SQLException {
        // TODO 自动生成的方法存根
        Connection cnn = null;
        try {

            // 获取数据库连接
            cnn = JDBCUtils.getConnection();
            // 开启事务
            cnn.setAutoCommit(false); // 禁止每条语句自动提交
            // 保存订单
            OrderDao orderDao = new OrderDaoImp();
            orderDao.saveOrder(cnn, orders);
            for (OrderItem orderItem : orders.getList()) {
                orderDao.saveOrderItem(cnn, orderItem);

            }

            // 提交事务
            cnn.commit();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            // 回滚
            cnn.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public PageModel findMyOrder(User user, int curNum) throws Exception {
        // TODO 自动生成的方法存根
        // 创建PageModel对象
        int pageSize = 3;
        OrderDao orderDao = new OrderDaoImp();
        int totalRecords = orderDao.findTotalRecords(user);
        PageModel pm = new PageModel(curNum, pageSize, totalRecords);
        List<Orders> list = orderDao.findMyOrderByUid(user, pm.getStartindex(), pm.getPageSize());
        pm.setList(list);
        pm.setUrl("OrderServlet?method=findMyOrder");
        return pm;
    }

    @Override
    public Orders findInfoByOid(String oid) throws Exception {
        // TODO 自动生成的方法存根
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.findInfoByOid(oid);
    }

    @Override
    public List<Orders> findAllOrders() throws SQLException {
        // TODO 自动生成的方法存根
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.findAllOrders();
    }

    @Override
    public List<Orders> findAllOrders(int st) throws SQLException {
        // TODO 自动生成的方法存根
        OrderDao orderDao = new OrderDaoImp();
        return orderDao.findAllOrders(st);
    }

}
