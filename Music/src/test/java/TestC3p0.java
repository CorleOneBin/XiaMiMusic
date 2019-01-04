import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class TestC3p0 {

    @Test
    public void test(){

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource ds = (DataSource) ac.getBean("dataSource");
        System.out.println(ds);

    }

}
