package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.CategoryDao;
import cn.xiami.dao.impl.MusicDao;
import cn.xiami.module.Category;
import cn.xiami.module.CinfoToMusic;
import cn.xiami.module.Music;
import cn.xiami.module.UserToMusic;
import cn.xiami.service.CategoryService;
import cn.xiami.service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * music的service实现类
 */
@Service("musicService")
public class MusicServiceImpl extends BaseServiceImpl<Music> implements MusicService {


    /**
     * 通过set方法给BaseviceImpl注入musicDao,
     */
    @Resource(name = "musicDao")
    public void setDao(BaseDao dao) {
        super.setDao(dao);
    }

     //给这个变量注入musicDaoo，调用除BaseDao外的方法
    @Resource(name = "musicDao")
     MusicDao dao;

    /**
     * 收藏歌曲
     * 即将cinfoID与musicId插入关联表
     * 并将cinfo的num加一
     */
    public boolean insertCinfoToMusic(int cinfoId, int musicId) {
        CinfoToMusic cinfoToMusic = new CinfoToMusic();
        cinfoToMusic.setCinfoId(cinfoId);
        cinfoToMusic.setMusicId(musicId);
        boolean flag = dao.insertCinfoToMusic(cinfoToMusic);
        if(flag){
            dao.updateCinfoNum(cinfoId);
        }
        return flag;
    }

    /**
     * 获取music的id
     * 即max(id) + 1
     */
    public Integer selectMusicId() {
        return dao.selectMaxId()+1;
    }

    /**
     *
     *
     *
     */
    public void insertUserToMusic(String phoneNumber, int musicId) {
        UserToMusic userToMusic = new UserToMusic();
        userToMusic.setPhoneNumber(phoneNumber);
        userToMusic.setMusicId(musicId);
        dao.insertUserToMusic(userToMusic);
    }

    @Override
    public List<Music> selectHistoryMusicByNumber(String phoneNumber) {
        return dao.selectHistoryMusicByNumber(phoneNumber);
    }


}
