package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.module.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员的ao类
 */
@Repository("adminDao")
public class AdminDao extends AbstractDao implements BaseDao<Admin> {

    public void insert(Admin admin) {

    }

    public void update(Admin admin) {

    }

    public void delete(Integer id) {

    }

    public Admin selectOne(Integer id) {
        return null;
    }

    public List<Admin> selectAll() {
        return null;
    }

    public Integer selectCount() {
        return null;
    }

}
