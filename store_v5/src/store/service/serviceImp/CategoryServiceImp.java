package store.service.serviceImp;

import java.util.List;

import redis.clients.jedis.Jedis;
import store.dao.CategoryDao;
import store.dao.daoImp.CategoryDaoImp;
import store.domain.Category;
import store.service.CategoryService;
import store.utils.JedisUtils;

public class CategoryServiceImp implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImp();

    @Override
    public List<Category> findAllCats() throws Exception {
        // TODO 自动生成的方法存根
        return categoryDao.findAllCats();
    }

    @Override
    public void addCategory(Category category) throws Exception {
        // TODO 自动生成的方法存根
        CategoryDao categoryDao = new CategoryDaoImp();
        categoryDao.addCategory(category);
        // 将Redis缓存清空,重新生成
        Jedis j = null;
        try {
            j = JedisUtils.getJedis();
            j.del("allcats");
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 释放jedis
            JedisUtils.closeJedis(j);
        }
    }

    @Override
    public void delCategory(String cid) throws Exception {
        // TODO 自动生成的方法存根
        CategoryDao categoryDao = new CategoryDaoImp();
        categoryDao.delCategory(cid);

        Jedis j = null;
        try {
            // 获取到Redis对象
            j = JedisUtils.getJedis();
            j.del("allcats");
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            JedisUtils.closeJedis(j);
        }

    }

}
