package store.dao.daoImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import store.dao.OrderDao;
import store.domain.OrderItem;
import store.domain.Orders;
import store.domain.Product;
import store.domain.User;
import store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

    @Override
    public void saveOrder(Connection cnn, Orders orders) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into orders value(?,?,?,?,?,?,?,?)";
        Object[] paramsObjects = {orders.getOid(), orders.getOrdertime(), orders.getTotal(), orders.getState(),
            orders.getAddress(), orders.getName(), orders.getTelephone(), orders.getUser().getUid()};
        queryRunner.update(cnn, sql, paramsObjects);
    }

    @Override
    public void saveOrderItem(Connection cnn, OrderItem orderItem) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into orderitem value(?,?,?,?,?)";
        Object[] paramObjects = {orderItem.getItemid(), orderItem.getQuanyity(), orderItem.getTotal(),
            orderItem.getProduct().getPid(), orderItem.getOrders().getOid()};
        queryRunner.update(cnn, sql, paramObjects);

    }

    @Override
    public int findTotalRecords(User user) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from orders where uid=?";
        // ScalarHandler返回指定列的值,query()返回值为一个Object
        Long count = (Long)queryRunner.query(sql, new ScalarHandler(), user.getUid());
        return count.intValue();
    }

    @Override
    public List<Orders> findMyOrderByUid(User user, int startIndex, int pageSize) throws Exception {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        // 按limit条件获取到订单
        String sql = "select * from orders where uid=? limit ?,?";
        List<Orders> list =
            queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class), user.getUid(), startIndex, pageSize);

        // 遍历每个订单
        for (Orders orders : list) {
            // 多表查询,查找订单号的
            sql = "select * from orderitem o,product p where oid=? and o.pid=p.pid";
            List<Map<String, Object>> list1 = queryRunner.query(sql, new MapListHandler(), orders.getOid());
            for (Map<String, Object> orders2 : list1) {

                // 将对应的数据封装到ordersitem
                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(orderItem, orders2);
                // 将对应的数据封装到product对象
                Product product = new Product();
                BeanUtils.populate(product, orders2);

                orderItem.setProduct(product);
                // 将orderitem放进orders中
                orders.getList().add(orderItem);
            }
            // 给每个订单添加对应的用户id
            orders.setUser(user);
        }

        return list;
    }

    @Override
    public Orders findInfoByOid(String oid) throws Exception {
        // TODO 自动生成的方法存根

        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders where oid = ? ";
        Orders orders = queryRunner.query(sql, new BeanHandler<Orders>(Orders.class), oid);

        // 获取到封装在orders对象list集合中的数据
        sql = "select * from orderitem o,product p where o.pid=p.pid and oid = ?";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), oid);

        for (Map<String, Object> map : list) {
            // 将map中的数据封装到orderitem中
            OrderItem orderItem = new OrderItem();
            BeanUtils.populate(orderItem, map);
            // 将map中的数据封装到produc中
            Product product = new Product();
            BeanUtils.populate(product, map);

            orderItem.setProduct(product);
            orderItem.setOrders(orders);

            // 将购物项添加到购物车
            orders.getList().add(orderItem);

        }

        return orders;
    }

    @Override
    public List<Orders> findAllOrders() throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders order by ordertime desc";
        List<Orders> list = queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class));
        return list;
    }

    @Override
    public List<Orders> findAllOrders(int st) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders where state=? order by ordertime desc";
        List<Orders> list = queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class), st);
        return list;
    }

}
