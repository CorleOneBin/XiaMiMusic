package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.CategoryDao;
import cn.xiami.module.Category;
import cn.xiami.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * category的service实现类
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {


    /**
     * 通过set方法给BsaeviceImpl注入categoryDao,
     */
    @Resource(name = "categoryDao")
    public void setDao(BaseDao dao) {
        super.setDao(dao);
    }

     //给这个变量注入categoryDao，调用除BaseDao外的方法
    @Resource(name = "categoryDao")
    CategoryDao dao;


    /*根据kind查询特色分类或者热门分类*/
    public List<Category> selectCate(Integer kind) {
        return dao.selectCate(kind);
    }
}
