package store.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.domain.User;
import store.service.UserService;
import store.service.serviceImp.UserServiceImp;
import store.utils.MailUtils;
import store.utils.MyBeanUtils;
import store.utils.UUIDUtils;
import store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1904165269415933020L;

    public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return "/jsp/register.jsp";
    }

    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        // 接收表单数据,把表单数据放到map集合中
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        // 将map中的属性值封装到user对象中
        MyBeanUtils.populate(user, map);
        user.setUid(UUIDUtils.getId());
        user.setCode(UUIDUtils.getCode());
        user.setState(0);
        // 在控制台打印一下user对象,校验一下表单是否个user赋值了
        // 调用业务层注册功能
        // 面向接口编程
        UserService userService = new UserServiceImp();
        try {
            userService.userRegister(user);
            // 注册成功向用户邮箱法信息,跳转到提示页面
            // request.setAttribute("msg", "用户注册成功,请激活");
            MailUtils.sendMail(user.getEmail(), user.getCode());
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            // 注册失败,跳转到提示页面
            request.setAttribute("msg", "注册失败");

        }

        return "/jsp/info.jsp";

    }

    public String active(HttpServletRequest request, HttpServletResponse response) {

        try {
            String code = request.getParameter("code");
            // 面向接口编程
            UserService userService = new UserServiceImp();
            userService.activeUser(code);
            // User user = new User();
            request.setAttribute("msg", "激活成功,请登录");
            return "/jsp/login.jsp";

        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            request.setAttribute("msg", "注册失败,请从新注册额");
        }
        return "/jsp/info.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/login.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        User user = MyBeanUtils.populate(User.class, map);
        UserService userService = new UserServiceImp();
        User loginUser = userService.login(user);
        if (loginUser != null) {
            String rememberme = request.getParameter("rememberme");
            if ("1".equals(rememberme)) {
                String value = user.getUsername();
                System.out.println(value);
                value = URLEncoder.encode(value, "utf-8");
                System.out.println(value);
                Cookie remembermeCookie = new Cookie("remembermeCookie", value);
                System.out.println(remembermeCookie);
                remembermeCookie.setPath("/");
                remembermeCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(remembermeCookie);
            } else {
                Cookie remembermeCookie = new Cookie("remembermeCookie", "");
                remembermeCookie.setPath("/");
                remembermeCookie.setMaxAge(0);
                response.addCookie(remembermeCookie);
            }

            String autoLogin = request.getParameter("autoLogin");
            if ("1".equals(autoLogin)) {
                // 注意cookie的值,创建cookie的时候要将cookie的值编码成UTF-8格式
                // cookie的value值不能是中文,因此需要进行编码,编码成UTF-8的编码形式,需要用到cookie的值得时候再进行解码
                String value = user.getUsername() + "@" + user.getPassword();
                System.out.println(value);
                value = URLEncoder.encode(value, "utf-8");
                System.out.println(value);
                Cookie autoLoginCookie = new Cookie("autoLoginCookie", value);
                System.out.println(autoLoginCookie);
                autoLoginCookie.setPath("/");
                autoLoginCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(autoLoginCookie);
            } else {
                Cookie autoLoginCookie = new Cookie("autoLoginCookie", "");
                autoLoginCookie.setPath("/");
                autoLoginCookie.setMaxAge(0);
                response.addCookie(autoLoginCookie);

            }
            // 将loginUser放到session中
            request.getSession().setAttribute("loginUser", loginUser);
            response.sendRedirect(request.getContextPath() + "/");
            return null;

        } else {
            request.setAttribute("msg", "密码不正确或用户不存在");
            // 重定向没有显示提示信息
            // response.sendRedirect(request.getContextPath()+"/jsp/info.jsp");
            return "/jsp/login.jsp";
        }
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将session用户状态信息移除
        request.getSession().removeAttribute("userlogin");
        // 重新定向到首页
        response.sendRedirect(request.getContextPath() + "/UserServlet?method=loginUI");
        // 不适用BaseServlet转发
        return null;
    }

}
