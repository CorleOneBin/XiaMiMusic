package cn.xiami.service.impl;

import cn.xiami.dao.BaseDao;
import cn.xiami.dao.impl.CategoryDao;
import cn.xiami.dao.impl.UserDao;
import cn.xiami.module.Category;
import cn.xiami.module.User;
import cn.xiami.service.CategoryService;
import cn.xiami.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * user的service实现类
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    /**
     * 通过set方法给BaseviceImpl注入userDao,
     */
    @Resource(name = "userDao")
    public void setDao(BaseDao dao) {
        super.setDao(dao);
    }

     //给这个变量注入userDao，调用除BaseDao外的方法
    @Resource(name = "userDao")
     UserDao dao;




}
