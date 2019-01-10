import org.apache.log4j.Logger;
import org.junit.Test;

public class Testlog4j {

    private static Logger logger1 = Logger.getLogger(Testlog4j.class);

    @Test
    public void testLog4j(){
        String name = "adfadf";
            logger1.info(name);
    }


}
