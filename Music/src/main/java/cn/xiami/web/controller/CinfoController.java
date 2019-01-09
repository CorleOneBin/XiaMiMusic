package cn.xiami.web.controller;

import cn.xiami.module.Cinfo;
import cn.xiami.service.CinfoService;
import cn.xiami.util.MyUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public void  createCinfo(HttpServletRequest req, HttpServletResponse resp/*,@RequestParam MultipartFile uploadFile*/) throws IOException {
        req.setCharacterEncoding("utf-8");
        DiskFileItemFactory dif = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(dif);
        List<FileItem> fil = null;

        //种类名字
        String cateName[] =  null;
        String cateNames = null;
        //种类的Id
        String cateId[] = null;
        String cateIds = null;
        //歌单的名字
        String cinfoName = null;
        //用户名
        String phoneNumber = null;
        //歌单简介
        String desc = null;
        String imgHref = null;

        try {
            fil =  sfu.parseRequest(req);
            for (FileItem fim : fil)  {
                if(fim.isFormField()){
                    if("cateName".equals(fim.getFieldName())){
                        cateNames = fim.getString();
                    }else if("cateId".equals(fim.getFieldName())){
                        cateIds = fim.getString();
                    }else if("phoneNumber".equals(fim.getFieldName())){
                        phoneNumber = fim.getString();
                    }else if("cinfoName".equals(fim.getFieldName())){
                        cinfoName = fim.getString();
                    }else if("descr".equals(fim.getFieldName())){
                        desc = fim.getString();
                    }
                }else{
                    String path = req.getServletContext().getRealPath("/image/upload");
                    String fileName = UUID.randomUUID()+ fim.getName().substring(fim.getName().lastIndexOf("."));
                    imgHref = "/image/upload/"+fileName;
                    fim.write(new File(path,fileName));
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        cateId = cateIds.split(",");

        int cinfoId = cs.selectId();

        Cinfo cinfo = new Cinfo();
        //随便取其中一个种类的名字为其tag
        cinfo.setTag("tag");
        cinfo.setDescri(desc);
        cinfo.setName(cinfoName);
        cinfo.setNum(0);
        cinfo.setImgHref(imgHref);
        cinfo.setId(cinfoId);


        //插入对应的cinfo到基本表
        cs.insert(cinfo);


        //插入cinfo对应的cate到关联表
        for(int i = 0;i < cateId.length; i++){
            cs.insertCatetoCinfo(Integer.parseInt(cateId[i]),cinfoId);
        }

        //插入cinfo对应的user到关联表
        cs.insertUsertoCinfo(phoneNumber,cinfoId);

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



