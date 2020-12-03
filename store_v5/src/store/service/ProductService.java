package store.service;

import java.sql.SQLException;
import java.util.List;

import store.domain.PageModel;
import store.domain.Product;

public interface ProductService {

    List<Product> find_new() throws SQLException;

    List<Product> find_host() throws SQLException;

    Product findById(String pid) throws SQLException;

    PageModel findByNumAndPage(int cNum, String cid) throws SQLException;

    PageModel findProduct(int num) throws SQLException;

    void addProduct(Product product) throws SQLException;

}
