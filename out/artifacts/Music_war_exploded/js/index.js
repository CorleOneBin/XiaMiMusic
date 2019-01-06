
/*将秒转换为时间*/
function timeFormat(number) {
    var minute = parseInt(number / 60);
    var second = parseInt(number % 60);
    minute = minute >= 10 ? minute : "0" + minute;
    second = second >= 10 ? second : "0" + second;
    return minute + ":" + second;
}

/**
 *
 * 获取项目的根路径，相当于 <c:url value="">
 */
function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

window.onload=function(){

    /*登录面板==================================*/
    /*播放视频*/
    $("#toLoginPanel").click(function(){
       var video = document.getElementById("login-video");
       if(video.paused){
           video.play();
       }
       /*点击登录/注册，初始化所有的页面*/
        $("#login").show();
        $("#register").hide();
        $("#register1").hide();
        $("#username").val("");
        $("#password").val("");
        $("#usernumber").val("");
        $("#security-code").val("");
        $("#regispassword").val("");
        $("#repassword").val("");
        $("#usernumberMsg").text("");
    });
    /*从登录到注册*/
    $("#toregister").click(function(){
        $("#login").hide();
        $("#register").show();
        $("#username").val("");
        $("#password").val("");
    });
    /*从注册到登录*/
    $("#backtoLogin").click(function(){
        $("#register").hide();
        $("#login").show();
        $("#usernumber").val("");
        $("#security-code").val("");
    });
   /* /!*填完手机号码，验证码点击下一步*!/
    $("#next").click(function(){
        $("#register").hide();
        $("#usernumber").val("");
        $("#security-code").val("");
        $("#register1").show();
    });*/
    /*返回上一步*/
    $("#backtoLastRegis").click(function(){
        $("#register").show();
        $("#register1").hide();
        $("#regispassword").val("");
        $("#repassword").val("");
    });

    /*登录注册正则与ajax判断========================================*/
    //注册判断
    $("#usernumber").blur(function(){
       var usernumber = $("#usernumber").val();
       if(!(/^[1][2,3,4,5,6,7,8,9][0-9]{9}$/.test(usernumber))){
           $("#usernumberMsg").text("请填写正确的手机号码")
           $("#usernumberMsg").css("color","red");
           $(".sendCode").css("background-color","#656565");
       }else{
           $.get(getRootPath()+"/user/judgeNumber",{number:usernumber},function (data) {
               if("false" == data){
                   $("#usernumberMsg").text("该手机号已被注册")
                   $("#usernumberMsg").css("color","red");
                   $(".sendCode").css("background-color","#656565");
                   return;
               }
           });
           $("#usernumberMsg").text("手机号码可以被注册")
           $("#usernumberMsg").css("color","green");
           $(".sendCode").css("background-color","#ff410f");   /*判断成功后，让发送验证码按钮显示为红色*/
       }
    });

    /*发送验证码*/
    $(".sendCode").click(function(){
        var usernumber = $("#usernumber").val();
        var backColor = $(".sendCode").css("background-color")
        if(backColor=="rgb(255, 65, 15)"){
            $.get(getRootPath()+"/user/sendCode",{number:usernumber},function(data){

            })
        }
    });
    /*使下一步可以点击*/
    $("#security-code").blur(function(){
        var usernumber = $("#usernumber").val();
        var code = $("#security-code").val();
        if(usernumber.length != 0 && code.length!=0){
            $("#next").css("background-color","#ff410f");
        }else{
            $("#next").css("background-color","#656565");
        }
    });

    /*点击下一步*/
    $("#next").click(function(){
        var usernumber = $("#usernumber").val();
        var code = $("#security-code").val();
        $.get("/user/registerNext",{number:usernumber,code:code},function(data){
                if( data == "false" ){
                    alert("验证码错误");
                }else{
                    $("#register").hide();
                    $("#register1").show();
                    $("#repassword").next().text("");
                    $("#regispassword").next().text("");
                }
        });
});

    /*验证注册密码是否正确*/
    $("#regispassword").blur(function(){
       var password = $("#regispassword").val();
       if(password.length<6 || password.length > 12){
           $("#regispassword").next().text("请输入6-12位的密码");
           $("#regispassword").next().css("color","red");
       }else{
           $("#regispassword").next().text("");
       }
    });
    /*验证重复密码*/
    $("#repassword").blur(function(){
        var password = $("#regispassword").val();
        var repassword = $("#repassword").val();
        if(password != repassword){
            $("#repassword").next().text("两次密码不一致");
            $("#repassword").next().css("color","red");
        }else{
            $("#repassword").next().text("");
            $(".register1 .next").css("background-color","#ff410f");
        }
    });


    /*登录验证，是否可以点击登录按钮*/
    $("#password").blur(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        if(username.length >0 && password.length >0){
            $(".login .next").css("background-color","#ff410f");
        }
    });

    /*登录*/
    $("#loginBtn").click(function () {
        var backColor=$("#loginBtn").css("background-color");
        var username = $("#username").val();
        var password = $("#password").val();
        if(backColor == "rgb(255, 65, 15)"){
            $.get(getRootPath()+"/user/login",{username:username,password:password},function(data){
                    if("true" == data){
                        window.location.reload();
                    }else{
                        alert("账号或密码错误");
                    }
            });
        }
    });

    var audio = document.getElementById("audio-music");
    /*暂停与播放*/
    $("#playBtn").click(function () {
        if(audio != null){
            if(audio.paused){
                audio.play();
                $("#playBtn").removeClass("fa fa-play-circle");
                $("#playBtn").addClass("fa fa-pause-circle");
            }else{
                $("#playBtn").removeClass("fa fa-pause-circle");
                $("#playBtn").addClass("fa fa-play-circle");
                audio.pause();
            }
        }
    })

    /*进度条========================================================================================================*/
    var totalTime = audio.duration;
    $("#total-time").text(timeFormat(totalTime));

    /*进度条拖拽*/
    $("#handle").mousedown(function(ev){
        var disx = ev.pageX - $(this).offset().left;
        $(document).mousemove(function(ev){

            totalTime = audio.duration;
            var x = ev.pageX - disx;
            var percent = (x / 1274)*94.5;                /*得出百分比,95是因为拖拽块占了一部分长度*/
            if(percent<=95 && percent>=-1){
                $("#play-progress-bar").css("width",percent+"%"); /*设置进度条的长度*/
                $("#handle").css("margin-left",percent+"%");  /*设置拖拽块位置*/
                $("#current-time").text(timeFormat(totalTime*(x / 1274)));/*设置拖拽后的当前时间*/
                audio.currentTime=totalTime*(x / 1274);  /*将音乐调到这个时间播放*/
            }
        });
        $(document).mouseup(function(){
            $(document).off();
        });
        return false;
    });

    /*一秒钟让进度条加一次*/
     var Timespan = setInterval(function(){
            var currentTime = audio.currentTime+1;
            totalTime = audio.duration;
            var percent = (currentTime/totalTime)*94.5;
            $("#play-progress-bar").css("width",percent+"%"); /*设置进度条的长度*/
            $("#handle").css("margin-left",percent+"%");  /*设置拖拽块位置*/
            $("#current-time").text(timeFormat(currentTime));/*设置加了一秒后的时间*/
            $("#total-time").text(timeFormat(totalTime));
        }, 1000);

    /*音量====================================================================================================*/
    $("#volume-handle").mousedown(function(ev){
        var disx = ev.pageX - $(this).offset().left;
        $(document).mousemove(function(ev){
            var x = ev.pageX - disx - 997;
            var percent = x / 100;  
            if(x >=20  && x <= 120){
                $("#volume-handle").css("left",x);      /*音量拖动*/
                x = x-20;
                $("#volume-bar").css("width",x+"%");    /*音量进度条*/
                audio.volume=x/100;                    /*音量*/
            }
        });
        $(document).mouseup(function () {
            $(document).off();
        });
        return false;
    });


    /*搜索框的变化*/
    $("#search").click(function(){
        $("#search").animate({
            width: '250px'
        },100);
    });

    $("#search").blur(function () {
        $("#search").animate({
            width:'105px'
        },100);
    });



}




$(document).ready(function(){

    /*面板*/
    $(".my-panel").fadeIn(2000);


    /*推荐板块*/
    $(".collect-container .block-list img").hover(function () {
       $(this).next().show();
    },function(){
        $(this).next().hide();
    });



});
