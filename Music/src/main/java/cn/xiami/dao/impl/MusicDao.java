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

    }

    public void update(Music music) {

    }

    public void delete(Integer id) {

    }

    public Music selectOne(Integer id) {
        return null;
    }

    public List<Music> selectAll() {
        return null;
    }

    public Integer selectCount() {
        return null;
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

}
