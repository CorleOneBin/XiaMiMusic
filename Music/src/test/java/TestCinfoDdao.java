import cn.xiami.dao.impl.CinfoDao;
import cn.xiami.module.Cinfo;
import cn.xiami.module.Music;
import cn.xiami.web.controller.CinfoController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestCinfoDdao {

    @Test
    public void testSelectOne(){

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CinfoDao cd = (CinfoDao) ac.getBean("cinfoDao");
        Cinfo cinfo =  cd.selectOne(1911);
        List<Music> musicList =  cinfo.getMusics();
        for (Music music : musicList) {
            System.out.println(music.getName());
        }


    }

}
