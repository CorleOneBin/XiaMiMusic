package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.module.Category;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;

/**
 * 歌单种类的Dao类
 */
@Repository("categoryDao")
public class CategoryDao extends AbstractDao implements BaseDao<Category> {

    public void insert(Category category) {

    }

    public void update(Category category) {

    }

    public void delete(Integer id) {
    }

    /**
     * 查出一个歌单的种类，包括其下的所有歌单
     */
    public Category selectOne(Integer id) {
        return getSqlSession().selectOne("category.selectOne",id);
    }

    /**
     * 查出所有的歌单种类，但不查出其下的歌单
     */
    public List<Category> selectAll() {
        return getSqlSession().selectList("category.selectAll");
    }

    public Integer selectCount() {
        return null;
    }

    /**
     * 根据kind查询特色分类或者热门分类
     */
    public List<Category> selectCate(Integer kind){
       return getSqlSession().selectList("category.selectCate",kind);
    }

}
