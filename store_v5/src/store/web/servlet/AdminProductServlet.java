package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.Category;
import store.domain.PageModel;
import store.service.CategoryService;
import store.service.ProductService;
import store.service.serviceImp.CategoryServiceImp;
import store.service.serviceImp.ProductServiceImp;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    // 查找商品
    public String findAllProducts(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int num = Integer.parseInt(req.getParameter("num"));
        ProductService productService = new ProductServiceImp();
        PageModel pm = productService.findProduct(num);
        req.setAttribute("page", pm);
        return "/admin/product/list.jsp";
    }

    public String saveUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> list = categoryService.findAllCats();
        req.setAttribute("allcats", list);
        return "/admin/product/add.jsp";
    }

    // 添加商品
    public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/admin/product/list.jsp";
    }
}
