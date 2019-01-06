import cn.xiami.dao.impl.UserDao;
import cn.xiami.module.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserDao {


    @Test
    public void testSelectOne(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = (UserDao) ac.getBean("userDao");
        User user = dao.selectOneByNumber("16674205184");
        System.out.println(user.getPhoneNumber());
    }

    @Test
    public void testUpdateUser(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = (UserDao) ac.getBean("userDao");
        User user = new User();
        user.setNickName("bin123");
        user.setDescription("这是一段描述");
        user.setPhoneNumber("15274464875");
        dao.update(user);
    }

}
