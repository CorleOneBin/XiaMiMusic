package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.CinfoDao;
import cn.xiami.module.CateToCinfo;
import cn.xiami.module.Cinfo;
import cn.xiami.module.UserToCinfo;
import cn.xiami.service.CinfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 创建歌单时获取ID，在最大的Id上加1
     */
    public Integer selectId() {
        return dao.selectId()+1;
    }

    /**
     * 插入用户对应cinfo的关联表
     */
    public void insertUsertoCinfo(String phoneNumber, int cinfoId) {
        UserToCinfo userToCinfo = new UserToCinfo();
        userToCinfo.setPhoneNumber(phoneNumber);
        userToCinfo.setCinfoId(cinfoId);
        dao.insertUsertoCinfo(userToCinfo);
    }

    /**
     * 插入cate对应cinfo的关联表
     */
    public void insertCatetoCinfo(int cateId, int cinfoId) {
        CateToCinfo cateToCinfo = new CateToCinfo();
        cateToCinfo.setCateId(cateId);
        cateToCinfo.setCinfoId(cinfoId);
        dao.insertCatetoCinfo(cateToCinfo);
    }
}
