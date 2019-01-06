package cn.xiami.web.controller;

import cn.xiami.module.User;
import cn.xiami.service.UserService;
import cn.xiami.util.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("userController")
public class UserController {

    @Resource(name="userService")
    UserService us;


    /**
     * 用于ajax判断账号是否被注册
     */
    @RequestMapping(value = "/user/judgeNumber")
    public void judgeUserNumber(@RequestParam("number") String number, HttpServletResponse resp){
        try {

            /*判断是否可以注册*/
            boolean b = us.judgeNumber(number);
            resp.getWriter().print(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ajax实现点击发送验证码
     */
    @RequestMapping(value = "/user/sendCode")
    public void sendCode(@RequestParam("number") String number, HttpSession session,HttpServletResponse resp){
        try {
            resp.getWriter().print("hello word");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //随机返回一个验证码
        int veriCode = MyUtil.Random();
        //发送验证码
        MyUtil.sendVerification(number,veriCode);
        //将验证码存入到session内存中,等下进行登录验证
        session.setAttribute(number,veriCode);

    }

    /**
     * ajax实现点击下一步
     * 验证验证码是否正确
     */
    @RequestMapping(value = "/user/registerNext")
    public void registerNext(HttpServletRequest req,HttpServletResponse resp){
        try {

            HttpSession session =  req.getSession();
            String number = req.getParameter("number");
            session.setAttribute("number",number);
            int code = Integer.parseInt(req.getParameter("code"));
            //取出发送的验证码
            Integer code1 = (Integer) session.getAttribute(number);
            //判断验证码是否输入正确
            if(code1 == null){
                resp.getWriter().print(false);
            }else if(code1 == code){
                resp.getWriter().print(true);
            }else{
                resp.getWriter().print(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     *实现用户注册
     */
    @RequestMapping(value = "/user/register")
    public String register(HttpServletRequest req,HttpSession session){
        String phoneNumber = (String) session.getAttribute("number");
        String password = req.getParameter("regispassword");
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        us.insert(user);
        return "redirect:/index.jsp";
    }


    /**
     * 实现用户的登录
     */
    @RequestMapping(value = "/user/login")
    public void login(HttpServletRequest req,HttpServletResponse resp){

        HttpSession session = req.getSession();
        String username =  req.getParameter("username");
        String password = req.getParameter("password");

        boolean canLogin =  us.judgeLogin(username,password);
        User user = us.selectOneByNumber(username);
        //如果登录了，就放入session中
        if(canLogin){
            session.setAttribute("user",user);
        }

        try {
            resp.getWriter().print(canLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 注销登录
     */
    @RequestMapping(value = "/user/logOut")
    public String logOut(HttpSession session){
        //设置user的值为空
        session.setAttribute("user",null);
        return "redirect:/index.jsp";
    }

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "/user/updateUser")
    public String updateUser(HttpServletRequest req){

        HttpSession session = req.getSession();
        String nickName = req.getParameter("nickName");
        String desc = req.getParameter("signature");
        String phoneNumber = req.getParameter("phoneNumber");

        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setDescription(desc);
        user.setNickName(nickName);
        //更新数据库信息
        us.update(user);

        //更新session里面的信息
        user =  us.selectOneByNumber(phoneNumber);
        session.setAttribute("user",user);
        return "redirect:/index.jsp";
    }


    /**
     * 创建完歌单后，用户的信息相当于更新了。 session里面的user也要更新
     */
    @RequestMapping(value = "/user/updateSessionUser")
    public String updateSesssionUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        user = us.selectOneByNumber(user.getPhoneNumber());
        session.setAttribute("user",user);
        return "redirect:/myMusic.jsp";
    }

}
