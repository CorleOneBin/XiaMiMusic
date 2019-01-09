import cn.xiami.util.MyUtil;
import org.junit.Test;

public class TestSendMesage {

    /*@Test
    public void testSendMessage(){
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init("app.cloopen.com", "8883");
        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
        restAPI.setAccount("8aaf07086812057f016816a6c3880237", "f71d80219b824556b2250b3c0bf48d38");
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAppId("8aaf07086812057f016816a6c3da023d");
        // 请使用管理控制台中已创建应用的APPID。
        result = restAPI.sendTemplateSMS("15274464875","1" ,new String[]{"验证码为1111","2"});
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
    }*/

    @Test
    public void testRandom(){
        int a = MyUtil.Random();
        System.out.println(a);
    }

}

