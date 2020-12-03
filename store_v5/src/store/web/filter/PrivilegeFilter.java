package store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivilegeFilter implements Filter {
    public PrivilegeFilter() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        Object loginUser = httpServletRequest.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            httpServletRequest.setAttribute("msg", "您还没有登录,没有访问限权");
            // RequestDispatcher 对象将请求转发给资源，或者在响应中包含资源
            httpServletRequest.getRequestDispatcher("/jsp/login.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
