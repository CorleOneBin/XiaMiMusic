package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.module.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户的Dao类
 */
@Repository("userDao")
public class UserDao extends AbstractDao implements BaseDao<User> {

    public void insert(User user) {

    }

    public void update(User user) {

    }

    public void delete(Integer id) {

    }

    public User selectOne(Integer id) {
        return null;
    }

    public List<User> selectAll() {
        return null;
    }

    public Integer selectCount() {
        return null;
    }

}
