package cn.xiami.service;

import cn.xiami.module.Cinfo;

/**
 *  cinfo 的接口，放除BaseService外的其他特殊的业务
 */
public interface CinfoService extends BaseService<Cinfo>{

    //创建歌单时获取ID，在最大的Id上加1
    Integer selectId();

    //插入用户对应cinfo的关联表
    void insertUsertoCinfo(String phoneNumber,int cinfoId);

    //插入cate对应cinfo的关联表
    void insertCatetoCinfo(int cateId, int cinfoId);

}
