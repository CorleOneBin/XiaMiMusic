package cn.xiami.web.controller;

import cn.xiami.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller("userController")
public class UserController {

    @Resource(name="userService")
    UserService us;


}
