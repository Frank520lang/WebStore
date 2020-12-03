package store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import store.dao.ProductDao;
import store.domain.PageModel;
import store.domain.Product;
import store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

    @Override
    public List<Product> find_new() throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pflag=0 order by pdate desc limit 0,9";
        List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return list;
    }

    @Override
    public List<Product> find_hot() throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where is_hot=0 order by pdate desc limit 0,9";
        List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return list;
    }

    @Override
    public Product findById(String pid) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        Product product = queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
        return product;
    }

    @Override
    public int findByCid(String cid) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from product where cid=?";
        Long num = (long)queryRunner.query(sql, new ScalarHandler(), cid);
        return num.intValue();
    }

    @Override
    public List findByNumAndPage(String cid, int startindex, int pageSize) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where cid =? limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), cid, startindex, pageSize);
    }

    @Override
    public int findtotalRecords() throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from product";
        Long num = (Long)queryRunner.query(sql, new ScalarHandler());
        return num.intValue();
    }

    @Override
    public List<Product> findProduct(int startindex, int pageSize) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product order by pdate limit ?,?";
        List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), startindex, pageSize);
        return list;
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
            product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
            product.getCid()};
        queryRunner.update(sql, params);
    }

}
