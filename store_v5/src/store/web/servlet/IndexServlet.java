package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import store.domain.Category;
import store.domain.Product;
import store.service.CategoryService;
import store.service.ProductService;
import store.service.serviceImp.CategoryServiceImp;
import store.service.serviceImp.ProductServiceImp;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // TODO 自动生成的方法存根
        // CategoryService categoryService = new CategoryServiceImp();
        // List<Category> list = categoryService.findAllCats();
        // //放入到request域对象中
        // req.setAttribute("allCats",list);

        ProductService productService = new ProductServiceImp();
        List<Product> list_hots = productService.find_host();
        List<Product> list_news = productService.find_new();
        req.setAttribute("hots", list_hots);
        req.setAttribute("news", list_news);

        return "/jsp/index.jsp";

    }

}
