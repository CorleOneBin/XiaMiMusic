package cn.xiami.dao.impl;

import cn.xiami.dao.BaseDao;
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

}
