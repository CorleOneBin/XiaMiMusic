
/*将秒转换为时间*/
function timeFormat(number) {
    var minute = parseInt(number / 60);
    var second = parseInt(number % 60);
    minute = minute >= 10 ? minute : "0" + minute;
    second = second >= 10 ? second : "0" + second;
    return minute + ":" + second;
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
    /*填完手机号码，验证码点击下一步*/
    $("#next").click(function(){
        $("#register").hide();
        $("#usernumber").val("");
        $("#security-code").val("");
        $("#register1").show();
    });

    /*返回上一步*/
    $("#backtoLastRegis").click(function(){
        $("#register").show();
        $("#register1").hide();
        $("#regispassword").val("");
        $("#repassword").val("");
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

    $(".navbar li").click(function(){
        $(this).addClass('active').siblings().removeClass('active');
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

    $(function () {
        $('.rightbar-content .list .dowload span').click(function (event) {
            //取消事件冒泡
            event.stopPropagation();
            //按钮的toggle,如果div是可见的,点击按钮切换为隐藏的;如果是隐藏的,切换为可见的。
            $('.rightbar-content .list .collect').show();
            return false;
        });
        //点击空白处隐藏弹出层，下面为滑动消失效果和淡出消失效果。
        $(document).click(function(event){
            var _con = $('.rightbar-content .list .collect');   // 设置目标区域
            if(!_con.is(event.target) && _con.has(event.target).length === 0){ // Mark 1
                //$('#divTop').slideUp('slow');   //滑动消失
                $('.rightbar-content .list .collect').hide();          //淡出消失
            }
        });
    })

    /*点击歌曲，进行播放*/
    $(".m-name").click(function(){

        var mp3Url = $(this).next().attr("value");
        var name = $(this).text();
        var songer = $(this).next().next().text();
        var cinfoimgHref = $(this).next().next().next().attr("value");
        var audio = document.getElementById("audio-music");

        $("#audio-music").attr("src","http://"+mp3Url);
        $(".music  img").attr("src",cinfoimgHref);
        $(".music .info .content").text(name);
        $(".music .info .singer").text(songer);
        audio.play()
        $("#playBtn").removeClass("fa fa-play-circle");
        $("#playBtn").addClass("fa fa-pause-circle");
    });

});
