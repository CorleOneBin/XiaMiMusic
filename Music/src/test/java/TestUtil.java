import cn.xiami.util.MyUtil;
import org.junit.Test;

public class TestUtil {

    @Test
    public void testGetName(){
        for(int i = 0x4e00 ; i < 0x9fa5; i++){
            System.out.print((char)i);
            if((i-0x4e00)%70 == 0){
                System.out.println();
            }
        }
    }

    @Test
    public void testUtilGetName(){
        String name =  MyUtil.getName();
        System.out.println(name);
    }

}
