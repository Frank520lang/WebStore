package store.web.servlet;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.Cart;
import store.domain.CartItem;
import store.domain.OrderItem;
import store.domain.Orders;
import store.domain.PageModel;
import store.domain.User;
import store.service.OrderService;
import store.service.serviceImp.OrderServiceImp;
import store.utils.UUIDUtils;
import store.web.base.BaseServlet;

public class OrderServlet extends BaseServlet {

    private static final long serialVersionUID = 5379958770299955492L;

    public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "请先登录");
            return "/jsp/login.jsp";
        }
        // 从session中获取到购物车
        Cart cartSession = (Cart)request.getSession().getAttribute("cart");
        if (cartSession == null) {
            request.setAttribute("msg", "购物车中没有商品");
            return "/jsp/product_list.jsp";
        }

        // 创建订单对象
        Orders orders = new Orders();
        orders.setOid(UUIDUtils.getId());
        orders.setState(1);
        orders.setOrdertime(new Date());
        orders.setTotal(cartSession.getTotal());
        orders.setUser(loginUser);

        // 遍历购物车上的购物项
        for (CartItem cartItem : cartSession.getCartItem()) {
            // 将购物车上的对象转换成订单上的订单项对象
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtils.getId());
            orderItem.setQuanyity(cartItem.getCount());
            orderItem.setTotal(cartItem.getSubTotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrders(orders);
            // 将订单项添加到订单中
            orders.getList().add(orderItem);

        }

        // 调用服务层功能,保存订单
        OrderService orderService = new OrderServiceImp();
        orderService.save(orders);

        // 清空购物车
        cartSession.clear();

        // 页面转发
        request.setAttribute("orders", orders);
        return "/jsp/order_info.jsp";
    }

    public String findMyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取用户信息
        User user = (User)request.getSession().getAttribute("loginUser");
        // 获取当前页
        int curNum = Integer.parseInt(request.getParameter("num"));
        // 调用业务层功能
        OrderService orderService = new OrderServiceImp();
        PageModel pm = orderService.findMyOrder(user, curNum);
        // 转发
        request.setAttribute("page", pm);
        return "/jsp/order_list.jsp";

    }

    // 查看订单详情
    public String findInfoByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oid = request.getParameter("oid");
        OrderService orderService = new OrderServiceImp();
        Orders order = orderService.findInfoByOid(oid);
        request.setAttribute("orders", order);
        return "/jsp/order_info.jsp";
    }
}
