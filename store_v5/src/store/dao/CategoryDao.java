package store.dao;

import java.util.List;

import store.domain.Category;

public interface CategoryDao {

    List<Category> findAllCats() throws Exception;

    void addCategory(Category category) throws Exception;

    void delCategory(String cid) throws Exception;

}
