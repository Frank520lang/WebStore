package store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;
import store.dao.ProductDao;
import store.dao.daoImp.ProductDaoImp;
import store.domain.PageModel;
import store.domain.Product;
import store.service.ProductService;

public class ProductServiceImp implements ProductService {

    ProductDao productdao = new ProductDaoImp();

    @Override
    public List<Product> find_new() throws SQLException {
        // TODO 自动生成的方法存根
        return productdao.find_new();
    }

    @Override
    public List<Product> find_host() throws SQLException {
        // TODO 自动生成的方法存根
        return productdao.find_hot();
    }

    @Override
    public Product findById(String pid) throws SQLException {
        // TODO 自动生成的方法存根
        return productdao.findById(pid);
    }

    @Override
    public PageModel findByNumAndPage(int cNum, String cid) throws SQLException {
        // TODO 自动生成的方法存根
        ProductDao productDao = new ProductDaoImp();
        int totalRecords = productdao.findByCid(cid);
        PageModel pm = new PageModel(cNum, 12, totalRecords);
        List list = productDao.findByNumAndPage(cid, pm.getStartindex(), pm.getPageSize());
        pm.setList(list);
        pm.setUrl("ProductServlet?method=findByCidAndPage&cid=" + cid);
        return pm;
    }

    @Override
    public PageModel findProduct(int num) throws SQLException {
        // TODO 自动生成的方法存根r
        int pageSize = 5;
        ProductDao productDao = new ProductDaoImp();
        int totalRecords = productDao.findtotalRecords();
        PageModel pm = new PageModel(num, pageSize, totalRecords);
        List<Product> list = productDao.findProduct(pm.getStartindex(), pm.getPageSize());
        String url = "AdminProductServlet?method=findAllProducts";
        pm.setUrl(url);
        pm.setList(list);
        return pm;
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        // TODO 自动生成的方法存根
        ProductDao productDao = new ProductDaoImp();
        productDao.addProduct(product);
    }

}
