package cn.xiami.web.controller;

import cn.xiami.module.Category;
import cn.xiami.module.Cinfo;
import cn.xiami.service.CinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

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




}


