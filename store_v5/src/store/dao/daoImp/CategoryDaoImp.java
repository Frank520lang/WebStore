package store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import store.dao.CategoryDao;
import store.domain.Category;
import store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

    @Test
    public List<Category> findAllCats() throws Exception {
        String sql = "select * from category";
        QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSource());
        // 返回值是一个Category类的对象
        return qRunner.query(sql, new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void addCategory(Category category) throws Exception {
        // TODO 自动生成的方法存根
        String sql = "insert into category value(?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, category.getCid(), category.getCname());
    }

    @Override
    public void delCategory(String cid) throws Exception {
        // TODO 自动生成的方法存根
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        // 将商品的外键设置为空
        String sql = "update product set cid = null where cid = ?";
        queryRunner.update(sql, cid);
        // 删除分类
        sql = "delete from category where cid =?";
        queryRunner.update(sql, cid);

    }

}