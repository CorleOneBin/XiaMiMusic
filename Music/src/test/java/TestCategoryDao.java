import cn.xiami.dao.impl.CategoryDao;
import cn.xiami.module.Category;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCategoryDao {

    @Test
    public void testSelectOne(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryDao cd = (CategoryDao) ac.getBean("categoryDao");
        Category category =  cd.selectOne(27);
        System.out.println(category.getName());
    }


}
