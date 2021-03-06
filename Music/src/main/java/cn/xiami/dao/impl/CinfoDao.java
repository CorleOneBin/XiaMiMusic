package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.module.CateToCinfo;
import cn.xiami.module.Cinfo;
import cn.xiami.module.UserToCinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单的Dao
 */
@Repository("cinfoDao")
public class CinfoDao extends AbstractDao implements BaseDao<Cinfo> {

    public void insert(Cinfo cinfo) {
        getSqlSession().insert("cinfo.insert",cinfo);
    }

    public void update(Cinfo cinfo) {

    }

    public void delete(Integer id) {

    }

    public Cinfo selectOne(Integer id) {
        return getSqlSession().selectOne("cinfo.selectOne",id);
    }

    public List<Cinfo> selectAll() {
        return getSqlSession().selectList("cinfo.selectAll");
    }

    public Integer selectCount() {
        return null;
    }

    /**
     *获取最大的id
     */
    public Integer selectId() {
        return getSqlSession().selectOne("cinfo.selectMaxId");
    }

    public void insertUsertoCinfo(UserToCinfo userToCinfo){
        getSqlSession().insert("cinfo.insertUsertoCinfo",userToCinfo);
    }

    public void insertCatetoCinfo(CateToCinfo cateToCinfo){
        getSqlSession().insert("cinfo.insertCatetoCinfo",cateToCinfo);
    }
}
