package cn.xiami.web.controller;

import cn.xiami.module.Music;
import cn.xiami.service.MusicService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Controller("musicController")
public class MusicController {

    @Resource(name = "musicService")
    MusicService ms;

    /**
     * 下载歌曲
     */
    @RequestMapping(value = "/music/musicDownload")
    public void MusicDownload(HttpServletRequest req, HttpServletResponse resp){
        String mp3Href =  req.getParameter("mp3Url");
        String name =  req.getParameter("name");

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + name+".mp3");

        InputStream is = null;
        try {
             is = new URL("http://"+mp3Href).openConnection().getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=is.read(buffer)) !=-1){
                resp.getOutputStream().write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                resp.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 收藏歌曲
     */
    @RequestMapping(value = "/music/collectMusic")
    public void collectMusic(@RequestParam("cinfoId") int cinfoId,@RequestParam("musicId") int musicId,HttpServletResponse resp){

        boolean flag =  ms.insertCinfoToMusic(cinfoId,musicId);

        try {
            resp.getWriter().print(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取所有的歌曲
     */
    @RequestMapping(value = "/music/getAllMusic")
    public String getAllMusic(Model model){

        int pageSize = 20;
        int count = ms.selectCount();
        List<Music> musics =  ms.selectAll().subList(0,100);
        model.addAttribute("musics",musics);
        model.addAttribute("count",count);
        return "admin/member-list";

    }

    /**
     * 确认下架
     */
    @RequestMapping(value = "/music/soldOut")
    public void soldOut(HttpServletResponse resp){
        try {
            resp.getWriter().print("下架失败");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认删除
     */
    @RequestMapping(value = "/music/deleteMusic")
    public void deleteMusic(@RequestParam("musicId") int musicId,HttpServletResponse resp){
        ms.delete(musicId);
        try {
            resp.getWriter().print(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量删除确认
     */
    @RequestMapping(value = "/music/delteBatchMusic")
    public void deleteBatchMusic(HttpServletRequest req,HttpServletResponse resp){

        String[] musicIds = req.getParameterValues("musicId");
        for (String musicId : musicIds) {
            ms.delete(Integer.valueOf(musicId));
        }

        try {
            resp.getWriter().print(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加歌曲
     */
    @RequestMapping(value = "/music/addMusic")
    public String addMusic(HttpServletRequest req,HttpServletResponse resp){
        DiskFileItemFactory df = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(df);
        try {
            List<FileItem> list = sfu.parseRequest(req);
            Music music = new Music();
            int cinfoId = 0;
            for (FileItem fi : list) {
                if(fi.isFormField()){
                    String name = fi.getFieldName();
                    if("musicname".equals(name)){
                        music.setName(fi.getString());
                    }else if("songername".equals(name)){
                        music.setSonger(fi.getString());
                    }else if("album".equals(name)){
                        music.setAlbum(fi.getString());
                    }else if("cinfo".equals(name)){
                        cinfoId = Integer.parseInt(fi.getString());
                        System.out.println(cinfoId);
                    }
                }else{
                    String path = req.getServletContext().getRealPath("/image/uploadMusic");
                    String filename = UUID.randomUUID()+fi.getName().substring(fi.getName().lastIndexOf("."));
                    fi.write(new File(path,filename));
                    //mp3的路径
                    music.setMp3("/image/uploadMusic/"+filename);
                }
            }
            int musicId =  ms.selectMusicId();
            music.setId(musicId);
            //插入数据库
            ms.insertCinfoToMusic(cinfoId,musicId);
            ms.insert(music);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/member-list.jsp";
    }


    /**
     * 携带信息，跳转进入编辑页面
     */
    @RequestMapping(value = "/music/toEditPage")
    public String toEditPage(HttpServletRequest req){

        int musicId = Integer.parseInt(req.getParameter("musicId"));
        String songer = req.getParameter("songer");
        String album = req.getParameter("album");
        String name = req.getParameter("name");

        Music music = new Music();
        music.setId(musicId);
        music.setSonger(songer);
        music.setAlbum(album);
        music.setName(name);

        req.setAttribute("music",music);

        return "admin/member-edit";

    }

    /**
     * 编辑歌曲信息
     */

    @RequestMapping(value = "/music/editMusic")
    public String editMusic(HttpServletRequest req){

        String str = req.getParameter("musicId01");
        String musicName =  req.getParameter("musicname");

        int musicId = Integer.parseInt(req.getParameter("musicId01"));
        String songerName =  req.getParameter("songername");
        String album = req.getParameter("album");

        Music music = new Music();
        music.setId(musicId);
        music.setName(musicName);
        music.setSonger(songerName);
        music.setAlbum(album);
        ms.update(music);
        return "admin/member-list";
    }


}
