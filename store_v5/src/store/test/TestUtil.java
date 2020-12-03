package store.test;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;
import store.domain.User;

public class TestUtil {
    @Test
    public void test1() throws Exception {
        Map<String, String[]> map = new HashMap<String, String[]>();
        map.put("username", new String[] {"张三"});
        map.put("password", new String[] {"17707500994"});
        User user = new User();
        BeanUtils.populate(user, map);
        System.out.println(user);
    }

    @Test
    public void test2() throws Exception {
        Map<String, String[]> map = new HashMap<String, String[]>();
        map.put("username", new String[] {"张三"});
        map.put("password", new String[] {"17707500994"});
        map.put("birthday", new String[] {"1996-07-25"});
        User user = new User();
        // 1_创建时间类型的转换器
        DateConverter dt = new DateConverter();
        // 2_设置转换的格式
        dt.setPattern("yyyy-MM-dd");
        // 3_注册转换器
        // register(Converter converter, Class<?> clazz)
        // 注册Converter指定目的地 的自定义Class，替换以前注册的任何Converter。
        ConvertUtils.register(dt, java.util.Date.class);
        BeanUtils.populate(user, map);
        System.out.println(user);
    }

}
