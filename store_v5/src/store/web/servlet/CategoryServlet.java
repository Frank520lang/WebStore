
package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import store.domain.Category;
import store.service.CategoryService;
import store.service.serviceImp.CategoryServiceImp;
import store.utils.JedisUtils;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Jedis jedis = JedisUtils.getJedis();
        String jsonStr = jedis.get("allCats");
        if (jsonStr == null || "".equals(jsonStr)) {
            System.out.println("缓存中没有数据");
            CategoryService categoryService = new CategoryServiceImp();
            List<Category> list = categoryService.findAllCats();
            // 将list转换成json格式,用于网络传输,网络不能直接传输对象
            jsonStr = JSONArray.fromObject(list).toString();
            System.out.println(jsonStr);
            jedis.set("allCats", jsonStr);

        } else {
            System.out.println("缓存中有数据");

        }
        // 将结果响应给浏览器
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(jsonStr);
        JedisUtils.closeJedis(jedis);

        return null;
    }

}
