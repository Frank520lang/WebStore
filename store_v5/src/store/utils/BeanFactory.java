package store.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class BeanFactory {

    /*public static UserDao getUserDao(){
    	return new UserDaoImpl();
    }
    
    public static UserService getUserService(){
    	return new UserServiceImpl();
    }*/

    public static Object getBean(String id) {
        // 解析XML: dom4j:
        SAXReader reader = new SAXReader();
        Object obj = null;
        try {
            Document doc =
                reader.read(BeanFactory.class.getClassLoader().getResourceAsStream("applicationContext.xml"));
            // 使用xPath进行查找:
            Element element = (Element)doc.selectSingleNode("//bean[@id='" + id + "']");
            String value = element.attributeValue("class");
            // System.out.println(value);
            // 反射生成实例:
            Class clazz = Class.forName(value);
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        BeanFactory.getBean("userDao");
    }
}
