package store.web.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import store.domain.OrderItem;
import store.domain.Orders;
import store.service.OrderService;
import store.service.serviceImp.OrderServiceImp;
import store.web.base.BaseServlet;

public class AdminOrderServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public String findAllOrders(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String state = request.getParameter("state");
        // int st = Integer.parseInt(state);
        OrderService orderService = new OrderServiceImp();
        List<Orders> list = null;
        if (state == null) {
            list = orderService.findAllOrders();
        } else {
            int st = Integer.parseInt(state);
            list = orderService.findAllOrders(st);
        }
        request.setAttribute("list", list);
        return "/admin/order/list.jsp";
    }

    public String showDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        OrderService orderService = new OrderServiceImp();
        String oid = request.getParameter("oid");
        Orders orders = orderService.findInfoByOid(oid);
        List<OrderItem> orderItems = orders.getList();
        // 将list集合转化成json数组
        // 过滤器过滤掉,会陷入死循环的字段,对象里面包含对象
        JsonConfig cfg = new JsonConfig();
        cfg.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (name.equals("orders")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        JSONArray jsonArray = JSONArray.fromObject(orderItems, cfg);
        // 设置将发送到客户端的响应的内容类型，如果该响应尚未提交。给定内容类型可能包含字符编码规范，例如 text/html;charset=UTF-8。
        // 如果在调用 getWriter 之前调用此方法，则只根据给定内容类型设置响应的字符编码。
        response.setContentType("text/html;charset=utf-8");
        // 返回可将字符文本发送到客户端的 PrintWriter 对象。PrintWriter 使用 #getCharacterEncoding 返回的字符编码。
        // 如果未如 getCharacterEncoding 中所述指定响应的字符编码（即该方法只返回默认值 ISO-8859-1），则 getWriter 会将字符编码更新到 ISO-8859-1。
        response.getWriter().println(jsonArray.toString());
        return null;
    }
}
