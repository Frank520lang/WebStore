// package store.web.filter;
//
// import java.io.IOException;
// import java.net.URLDecoder;
// import java.sql.SQLException;
// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.FilterConfig;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import store.domain.User;
// import store.service.UserService;
// import store.service.serviceImp.UserServiceImp;
// import store.utils.CookUtils;
// public class LoginFilter implements Filter {
// @Override
// public void destroy() {
// // TODO 自动生成的方法存根
//
// }
//
// @Override
// public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
// throws IOException, ServletException {
// HttpServletRequest request = (HttpServletRequest) req ;
// HttpServletResponse response = (HttpServletResponse) resp;
// //如果是首页登录页面放行
// String servletPath = request.getServletPath();
// if (servletPath.startsWith("/UserServlet")) {
// String method = request.getParameter("method");
// if ("loginUI".equals(method)) {
// chain.doFilter(request, response);
// return;
// }
// }
//
// //用户登录信息
// User loginUser = (User) request.getSession().getAttribute("loginUser");
// if (loginUser != null) {
// chain.doFilter(request, response);
// return;
// }
// //获取自动登录的cookie信息
//
// Cookie userCookie = CookUtils.getCookieByName("autoLoginCookie", request.getCookies());
//
// //判断userCookie是否存在
// if (userCookie == null ) {
// chain.doFilter(request, response);
// return;
// }
//
// //通过cookie记录的信息查询用户
// //因为创建cookie的时对cookie的value进行了UTF-8编码,所以获取cookie的时候要解码userCookie.getValue(), "UTF-8");
// String u[] =URLDecoder.decode(userCookie.getValue(), "utf-8").split("@");
// String username = u[0];
// String password = u[1];
//
// //通过cookie创建一个User对象
// User user = new User(username,password);
//
// //执行登录
//
// try {
// UserService userService = new UserServiceImp();
// User userexitUser = userService.login(user);
// //查询登录
// if (userexitUser == null) {
// chain.doFilter(request, response);
// return ;
// }
// request.getSession().setAttribute("userexitUser",userexitUser);
// chain.doFilter(request, response);
// } catch (SQLException e) {
// // TODO 自动生成的 catch 块
// System.out.println("自动登录失败,自动忽略");
// }
//
// }
//
// @Override
// public void init(FilterConfig filterConfig) throws ServletException {
// // TODO 自动生成的方法存根
//
// }
//
// }
