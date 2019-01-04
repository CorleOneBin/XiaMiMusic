package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.service.BaseService;

import java.util.List;

/**
 *
 * BaseService的实现类，其他实现类都继承这个类。
 * 通过覆盖setDao方法来注入特殊的Dao类
 *
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao dao;

    public void setDao(BaseDao dao) {
        this.dao = dao;
    }

    public void insert(T t) {
        dao.insert(t);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public void update(T t) {
        dao.update(t);
    }

    public T selectOne(Integer id) {
        return (T) dao.selectOne(id);
    }

    public List<T> selectAll() {
        return dao.selectAll();
    }

    public Integer selectCount() {
        return dao.selectCount();
    }

}
