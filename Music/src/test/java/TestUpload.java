import cn.xiami.util.MyUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestUpload {

    @Test
    public void test(){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            String url = "D:\\暴风截图201911849307453.jpg";
            String path = "D:\\javaee\\IDEA_Project\\XiaMiMuisc\\Music\\web\\image\\upload\\";
            String fileName = url.substring(url.lastIndexOf('\\')+1);
            path = path+fileName;
            fis = new FileInputStream(url);
             fos = new FileOutputStream(path);
            byte[] bytes = new byte[1024];
            int len = 0;
            while( (len=fis.read(bytes))!=-1 ){
                fos.write(bytes,0,len);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testUpload(){
        String url = "D:\\暴风截图201911849307453.jpg";
        String path = "D:\\javaee\\IDEA_Project\\XiaMiMuisc\\Music\\web\\image\\upload\\";
        String srcUrl = MyUtil.uploadCinfoImg(url,path,"15274464875");
        System.out.println(srcUrl);
    }

}
