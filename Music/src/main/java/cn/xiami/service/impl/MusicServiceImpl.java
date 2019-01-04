package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.CategoryDao;
import cn.xiami.dao.impl.MusicDao;
import cn.xiami.module.Category;
import cn.xiami.module.Music;
import cn.xiami.service.CategoryService;
import cn.xiami.service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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




}
