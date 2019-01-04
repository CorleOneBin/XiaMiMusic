package cn.xiami.service;

import java.util.List;

/**
 * service的基本接口类
 */
public interface BaseService<T> {

    void insert(T t);
    void delete(Integer id);
    void update(T t);
    T selectOne(Integer id);
    List<T> selectAll();
    Integer selectCount();

}
