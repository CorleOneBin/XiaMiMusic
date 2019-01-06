package cn.xiami.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MyUtil {

    /**
     * 发送验证码
     */
    public static void sendVerification(String number,int veriCode){
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init("app.cloopen.com", "8883");
        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
        restAPI.setAccount("8aaf07086812057f016816a6c3880237", "f71d80219b824556b2250b3c0bf48d38");
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAppId("8aaf07086812057f016816a6c3da023d");
        // 请使用管理控制台中已创建应用的APPID。
        result = restAPI.sendTemplateSMS(number,"1" ,new String[]{"验证码为"+veriCode,"2"});
        System.out.println("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }

    /**
     * 返回一个4位的随机数字
     */
    public static int Random(){
        Random random = new Random();
        int result = random.nextInt(8999)+1000;
        return result;
    }

    /**
     * 上传cinfo的封面
     *
     * 参数为图片的本地路径 ， 目标路径  ， 用户的电话号码(用于标识)
     *
     * 返回存储的路径
     */
    public static String uploadCinfoImg(String localUrl,String srcPath,String phoneNumber){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            //取出文件名
            String fileName = localUrl.substring(localUrl.lastIndexOf('\\') + 1);
            fileName = phoneNumber+"_"+nowDate()+"_"+fileName;
            //拼接成目标路径
            srcPath = srcPath+"\\"+fileName;
            fis = new FileInputStream(localUrl);
            fos = new FileOutputStream(srcPath);
            byte[] bytes = new byte[1024];
            int len = 0;
            while( (len=fis.read(bytes))!=-1 ){
                fos.write(bytes,0,len);
            }
            return "image\\upload\\"+fileName;
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
        return null;
    }


    /**
     * 返回时间
     */
    public static String nowDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int y =  calendar.get(Calendar.YEAR);
        int m =  calendar.get(Calendar.MONTH)+1;
        int d =  calendar.get(Calendar.DAY_OF_MONTH);
        int H =  calendar.get(Calendar.HOUR_OF_DAY);
        int M =  calendar.get(Calendar.MINUTE);
        int s =  calendar.get(Calendar.SECOND);
        return y+"_"+m+"_"+d+"_"+H+"_"+M+"_"+s;
    }

}


