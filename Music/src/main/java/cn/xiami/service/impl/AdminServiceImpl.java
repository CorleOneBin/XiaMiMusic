package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.AdminDao;
import cn.xiami.dao.impl.CategoryDao;
import cn.xiami.module.Admin;
import cn.xiami.module.Category;
import cn.xiami.service.AdminService;
import cn.xiami.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Admin的service实现类
 */
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {


    /**
     * 通过set方法给baseServiceImpl注入adminDao,
     */
    @Resource(name = "adminDao")
    public void setDao(BaseDao dao) {
        super.setDao(dao);
    }

     //给这个变量注入adminDao，调用除BaseDao外的方法
    @Resource(name = "adminDao")
     AdminDao dao;

    public boolean judgeLogin(String username, String password) {
        return dao.judgeLogin(username,password);
    }
}
