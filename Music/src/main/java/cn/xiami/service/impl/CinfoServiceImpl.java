package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.CinfoDao;
import cn.xiami.module.Cinfo;
import cn.xiami.service.CinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * cinfo的service实现类
 */
@Service("cinfoService")
public class CinfoServiceImpl extends BaseServiceImpl<Cinfo> implements CinfoService {


    /**
     * 通过set方法给BsaeviceImpl注入cinfoDao,
     */
    @Resource(name = "cinfoDao")
    public void setDao(BaseDao dao) {
        super.setDao(dao);
    }

     //给这个变量注入cinfoDao，调用除BaseDao外的方法
    @Resource(name = "cinfoDao")
     CinfoDao dao;




}
