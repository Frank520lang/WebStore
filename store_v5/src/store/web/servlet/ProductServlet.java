package store.web.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.PageModel;
import store.domain.Product;
import store.service.ProductService;
import store.service.serviceImp.ProductServiceImp;
import store.utils.UUIDUtils;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public String findById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ProductService productService = new ProductServiceImp();
        String pid = request.getParameter("pid");
        Product product = productService.findById(pid);
        request.setAttribute("product", product);

        String ranStr = UUIDUtils.getId();
        // 在session存放一份随机字符串
        request.getSession().setAttribute("ranStr", ranStr);
        System.out.println(ranStr);

        return "/jsp/product_info.jsp";

    }

    public String findByCidAndPage(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String strNum = request.getParameter("num");
        int cNum = Integer.parseInt(strNum);
        String cid = request.getParameter("cid");
        ProductService productService = new ProductServiceImp();
        PageModel pm = productService.findByNumAndPage(cNum, cid);
        System.out.println(pm);
        request.setAttribute("page", pm);
        return "/jsp/product_list.jsp";
    }

}
