package cn.xiami.service;

import cn.xiami.module.User;

/**
 *  User 的接口，放除BaseService外的其他特殊的业务
 */
public interface UserService extends BaseService<User>{

    /*判断是否可以被注册*/
    boolean judgeNumber(String number);

    /*判断是否可以登录*/
    boolean judgeLogin(String number,String password);

    User selectOneByNumber(String number);

}
