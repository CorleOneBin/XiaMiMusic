package cn.xiami.service;

import cn.xiami.module.Category;

import java.util.List;

/**
 *  category 的接口，放除BaseService外的其他特殊的业务
 */
public interface CategoryService extends BaseService<Category>{

    //查询特色分类或者热门分类
    List<Category> selectCate(Integer kind);

    /*查出所有分类的id,kind,和name*/
    List<Category> selectAllCate();

}
