package store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 8912318262239818453L;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // localhost:8080/store/productServlet?method=addProduct
        // 以string的形式返回参数的值
        String method = req.getParameter("method");
        // method 没有,或者method为空
        if (null == method || "".equals(method) || method.trim().equals("")) {
            method = "execute";
        }

        // 注意:此处的this代表的是子类的对象
        // System.out.println(this);
        // 子类对象字节码对象
        Class<? extends BaseServlet> clazz = this.getClass();

        try {
            // 查找子类对象对应的字节码中的名称为method的方法.这个方法的参数类型是:HttpServletRequest.class,HttpServletResponse.class
            Method md = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            if (null != md) {
                // 执行相应的method方法
                String jspPath = (String)md.invoke(this, req, resp);
                if (null != jspPath) {
                    // 统一进行服务器转发
                    req.getRequestDispatcher(jspPath).forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 默认方法
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }

}