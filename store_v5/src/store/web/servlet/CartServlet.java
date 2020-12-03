package store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.Cart;
import store.domain.CartItem;
import store.domain.Product;
import store.domain.User;
import store.service.ProductService;
import store.service.serviceImp.ProductServiceImp;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public String addItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = (User)request.getSession().getAttribute("loginUser");
        if (user == null) {
            request.setAttribute("msg", "请先登录");
            return "/jsp/login.jsp";
        }
        // 获取到pid和count,并创建cartItem
        String pid = request.getParameter("pid");
        int count = Integer.parseInt(request.getParameter("quantity"));
        ProductService productService = new ProductServiceImp();
        Product product = productService.findById(pid);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCount(count);

        // 获取到购物车.从session中获取,直接使用
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        // 调用购物车上的添加购物项功能
        cart.addItemToCart(cartItem);
        // 重定向页面为
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }

    public String delCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pid = request.getParameter("pid");
        // 从session中获取到购物车(cart)
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.delCartItem(pid);
        // response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return "/jsp/cart.jsp";
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.clear();
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }
}
