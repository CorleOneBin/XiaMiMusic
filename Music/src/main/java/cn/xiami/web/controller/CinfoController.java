package cn.xiami.web.controller;

import cn.xiami.module.Cinfo;
import cn.xiami.service.CinfoService;
import cn.xiami.util.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 歌单的控制器
 *
 */
@Controller("cinfoController")
public class CinfoController {

    @Resource(name = "cinfoService")
    CinfoService cs;

    /**
     * 访问musicList时，先转到这里，获取相应的数据
     */
    @RequestMapping(value = "/cinfo/toMusicList")
    public String toMusicList(@RequestParam("cinfoId") int cinfoId, Model model){
        Cinfo cinfo =  cs.selectOne(cinfoId);
        model.addAttribute("cinfo",cinfo);
        return "musicList";
    }


    /**
     * 创建新的歌单
     */
    @RequestMapping(value = "/cinfo/createCinfo")
    public void  createCinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //种类名字
        String cateName[] =  req.getParameterValues("cateName");
        //种类的Id
        String cateId[] = req.getParameterValues("cateId");
        //歌单的名字
        String cinfoName = req.getParameter("cinfoName");
        //用户名
        String phoneNumber = req.getParameter("phoneNumber");
        //歌单简介
        String desc = req.getParameter("descr");
        //背景图片的本地路径
        String backUrl = req.getParameter("backUrl");
        backUrl = "D:\\"+backUrl.substring(backUrl.lastIndexOf("\\")+1);
        //存入图片
        String srcPath = req.getServletContext().getRealPath("/image/upload");
        //图片路径
        String imgHref  =  MyUtil.uploadCinfoImg(backUrl,srcPath,phoneNumber);
        //id
        int cinfoId = cs.selectId();

        Cinfo cinfo = new Cinfo();
        //随便取其中一个种类的名字为其tag
        cinfo.setTag(cateName[0]);
        cinfo.setDescri(desc);
        cinfo.setName(cinfoName);
        cinfo.setImgHref(imgHref);
        cinfo.setNum(0);
        cinfo.setId(cinfoId);


        //插入cinfo对应的cate到关联表
        for(int i = 0;i < cateId.length; i++){
            cs.insertCatetoCinfo(Integer.parseInt(cateId[i]),cinfoId);
        }

        //插入cinfo对应的user到关联表
        cs.insertUsertoCinfo(phoneNumber,cinfoId);

        //插入对应的cinfo到基本表
        cs.insert(cinfo);

        resp.getWriter().print(true);

    }


    /**
     * 管理页面，跳转到添加歌曲时，把cinfo带过去
     */
    @RequestMapping(value = "/cinfo/toAddMusic")
    public String toAddMusic(Model model){
        List<Cinfo> cinfos =  cs.selectAll();
        model.addAttribute("cinfos",cinfos);
        return "admin/member-add";
    }

}



