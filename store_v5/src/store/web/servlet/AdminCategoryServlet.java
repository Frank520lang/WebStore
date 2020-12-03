package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.Category;
import store.service.CategoryService;
import store.service.serviceImp.CategoryServiceImp;
import store.utils.UUIDUtils;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public String findAllcarts(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> list = categoryService.findAllCats();
        req.setAttribute("allcarts", list);
        return "/admin/category/list.jsp";
    }

    public String saveUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/admin/category/add.jsp";
    }

    public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String cname = req.getParameter("cname");
        Category category = new Category();
        category.setCid(UUIDUtils.getCode());
        category.setCname(cname);
        CategoryService categoryService = new CategoryServiceImp();
        categoryService.addCategory(category);
        resp.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllcarts");
        return null;
    }

    public String delCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String cid = req.getParameter("cid");
        CategoryService categoryService = new CategoryServiceImp();
        categoryService.delCategory(cid);
        resp.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllcarts");
        return null;
    }
}
