package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.module.Cinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单的Dao
 */
@Repository("cinfoDao")
public class CinfoDao extends AbstractDao implements BaseDao<Cinfo> {

    public void insert(Cinfo cinfo) {

    }

    public void update(Cinfo cinfo) {

    }

    public void delete(Integer id) {

    }

    public Cinfo selectOne(Integer id) {
        return getSqlSession().selectOne("cinfo.selectOne",id);
    }

    public List<Cinfo> selectAll() {
        return null;
    }

    public Integer selectCount() {
        return null;
    }

}
