import cn.xiami.dao.impl.MusicDao;
import cn.xiami.module.Music;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMusicDao {


    @Test
    public void testSelectAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        MusicDao musicDao = (MusicDao) ac.getBean("musicDao");
        List<Music> list =  musicDao.selectAll();
        for (Music music : list) {
            System.out.println(music.getName());
        }
    }



}
