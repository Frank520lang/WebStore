package store.dao;

import java.sql.SQLException;
import java.util.List;

import store.domain.PageModel;
import store.domain.Product;

public interface ProductDao {

    List<Product> find_new() throws SQLException;

    List<Product> find_hot() throws SQLException;

    Product findById(String pid) throws SQLException;

    int findByCid(String cid) throws SQLException;

    List findByNumAndPage(String cid, int startindex, int pageSize) throws SQLException;

    int findtotalRecords() throws SQLException;

    List<Product> findProduct(int startindex, int pageSize) throws SQLException;

    void addProduct(Product product) throws SQLException;

}
