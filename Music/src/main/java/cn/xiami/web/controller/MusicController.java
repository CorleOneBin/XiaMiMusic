package cn.xiami.web.controller;

import cn.xiami.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

}
