package cn.xiami.web.controller;

import cn.xiami.service.AdminService;
import cn.xiami.util.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller("adminController")
public class AdminController {

    @Resource(name = "adminService")
    AdminService as;

    @RequestMapping(value = "/admin/createCheckCode")
    public void createCheckCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 禁止缓存
        resp.setHeader("Cache-Control", "no-cache");
        //设置过期时间为立即过期
        resp.setDateHeader("Expires", 0);

        // 集合中保存所有成语
         List<String> words = new ArrayList<String>();
        // 初始化阶段，读取new_words.txt
        // web工程中读取 文件,必须使用绝对磁盘路径
        String path = req.getServletContext().getRealPath("/WEB-INF/words.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            //把读的成语全部添加到一个集合当中
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = 120;
        int height = 30;
        // 步骤一 绘制一张内存中图片
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 步骤二 图片绘制背景颜色 ---通过绘图对象
        Graphics graphics = bufferedImage.getGraphics();// 得到画图对象 --- 画笔
        // 绘制任何图形之前 都必须指定一个颜色
        graphics.setColor(MyUtil.getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        // 步骤三 绘制边框
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);
        // 步骤四 四个随机数字
        Graphics2D graphics2d = (Graphics2D) graphics;
        // 设置输出字体
        graphics2d.setFont(new Font("宋体", Font.BOLD, 18));
        Random random = new Random();// 生成随机数
        int index = random.nextInt(words.size());
        String word = words.get(index);// 获得成语
        // 定义x坐标
        int x = 10;
        for (int i = 0; i < word.length(); i++) {
            // 随机颜色
            graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 旋转 -30 --- 30度
            int jiaodu = random.nextInt(60) - 30;
            // 换算弧度
            double theta = jiaodu * Math.PI / 180;

            // 获得字母数字
            char c = word.charAt(i);

            // 将c 输出到图片
            graphics2d.rotate(theta, x, 20);
            graphics2d.drawString(String.valueOf(c), x, 20);
            graphics2d.rotate(-theta, x, 20);
            x += 30;
        }

        // 将验证码内容保存session
        req.getSession().setAttribute("checkcode", word);
        //把生成的验证码存放到全局域对象当中
        //this.getServletContext().setAttribute("checkCode", word);
        // 步骤五 绘制干扰线
        graphics.setColor(MyUtil.getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }
        // 将上面图片输出到浏览器 ImageIO
        graphics.dispose();// 释放资源
        //File img = new File("img/check_code.png");
        //将图片写到response.getOutputStream()中
        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());

    }


    @RequestMapping(value = "/admin/login")
    public String adminLogin(HttpServletRequest req){

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkcode = req.getParameter("checkcode");
        String preCode = (String) req.getSession().getAttribute("checkcode");

        if(as.judgeLogin(username,password) && preCode.equals(checkcode)){
            return "admin/index";
        }

        return "admin/login";
    }


}
