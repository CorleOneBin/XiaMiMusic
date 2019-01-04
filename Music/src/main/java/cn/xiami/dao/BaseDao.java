package cn.xiami.dao;

import java.util.List;

/**
 *
 * dao层的接口
 *
 */
public interface BaseDao<T> {

    void insert(T t);
    void update(T t);
    void delete(Integer id);
    T selectOne(Integer id);
    List<T>  selectAll();
    Integer selectCount();

}
