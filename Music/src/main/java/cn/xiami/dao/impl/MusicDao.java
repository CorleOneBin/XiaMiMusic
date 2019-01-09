package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.module.CinfoToMusic;
import cn.xiami.module.Music;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Music的dao类
 */
@Repository("musicDao")
public class MusicDao extends AbstractDao implements BaseDao<Music> {


    public void insert(Music music) {
        getSqlSession().insert("music.insert",music);
    }

    public void update(Music music) {
        getSqlSession().update("music.update",music);
    }

    public void delete(Integer id) {
        getSqlSession().delete("music.delete",id);
        getSqlSession().delete("music.deleteCinfoToMusic",id);
    }

    public Music selectOne(Integer id) {
        return null;
    }

    public List<Music> selectAll() {
        return getSqlSession().selectList("music.selectAll");
    }

    public Integer selectCount() {
        return getSqlSession().selectOne("music.selectCount");
    }

    /**
     * 插入关联表的想关信息
     */
    public boolean insertCinfoToMusic(CinfoToMusic cinfoToMusic) {
        try {
            getSqlSession().insert("music.insertCinfoToMusic",cinfoToMusic);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新cinfo的num
     *
     */
    public void updateCinfoNum(int cinfoId){
        getSqlSession().update("cinfo.updateCinfoNum",cinfoId);
    }

    public Integer selectMaxId() {
        return getSqlSession().selectOne("music.selectMaxId");
    }
}
