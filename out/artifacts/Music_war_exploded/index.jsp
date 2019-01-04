<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>虾米音乐</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link href="<c:url value='/css/reset.css'/> " rel="stylesheet" />
    <link href="<c:url value='/css/index.css'/> " rel="stylesheet" />
    <script src="<c:url value='/js/index.js'/> " type="text/javascript"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img class="logo" src="https://img.alicdn.com/tfs/TB1kdkmh3DqK1RjSZSyXXaxEVXa-216-60.png">
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">发现</a></li>
                <li><a href="<c:url value='/myMusic.jsp'/> ">我的音乐</a></li>
                <li><a href="#">音乐人</a></li>
                <li><a href="#">客户端下载</a></li>
                <li><a href="#">会员中心</a></li>
                <li><a href="#">回旧版</a></li>
                <li class="navbar-right login" data-toggle="modal" data-target="#login-panel">
                    <c:if test="${not empty sessionScope.user}">
                        <a href="#" id="toLoginPanel">${sessionScope.user.phoneNumber}</a>
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <a href="#" id="toLoginPanel">登录/注册</a>
                    </c:if>
                </li>
                <li class="navbar-right">
                    <form class="navbar-form search-bar" role="search">
                        <div class="form-group">
                            <input type="text" class="my-search" id="search" placeholder="Search">
                        </div>
                        <button type="submit" class="btn my-btn"><i class="fa fa-search"></i></button>
                    </form>
                </li>

            </ul>
        </div>

    </div>
</nav> <!--顶部导航栏-->
<div class="row my-row" style="margin-top: 69px; background-color: black;">   <!--轮播图-->
        <div class="col-sm-3">
            <div class="panel my-panel">
                <div class="my-panel-inner">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="#">推荐</a></h3>
                    </div>
                    <div class="panel-body my-panel-body">
                        <a href="#">排行榜</a>
                    </div>
                    <div class="panel-body my-panel-body">
                        <a href="<c:url value='/category/toCateList?cateId=27'/> ">歌单</a>
                    </div>
                    <div class="panel-body my-panel-body">
                        <a href="#">艺人</a>
                    </div>
                    <div class="panel-body my-panel-body">
                        <a href="#">专辑</a>
                    </div>
                    <div class="panel-body my-panel-body">
                        <a href="#"> MV</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 my-col-sm-9">
            <div id="myCarousel" class="carousel slide my-carousel">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="image/Carousel/test.jpg" alt="First slide">
                    </div>
                    <div class="item">
                        <img src="image/Carousel/test1.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="image/Carousel/test2.jpg" alt="Third slide">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div> <!--面板和轮播图-->
<div class="row my-row">
    <div class="col-sm-3"></div>
    <div class="col-sm-9">
        <div class="radio-container">
            <div class="radio-item">
                <img src="image/ratio_img/test.png"><br>
                <span class="radio-name">每日30首</span>
            </div>
            <div class="radio-item">
                <img src="image/ratio_img/test1.png"><br>
                <span class="radio-name">猜你喜欢</span>
            </div>
            <div class="radio-item">
                <img src="image/ratio_img/test2.png"><br>
                <span class="radio-name">听见不同</span>
            </div>
            <div class="radio-item">
                <img src="image/ratio_img/test3.png"><br>
                <span class="radio-name">私人电台</span>
            </div>
        </div>  <!--电台-->
        <div class="collect-container">
            <div class="block-title">
                <h2>推荐歌单</h2>
            </div>
            <div class="block-list">
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>
                <div class="collect-item">
                    <div class="wraper">
                        <img src="//pic.xiami.net/images/collect/15510601/632578215/34840328059e43eeb1413a2d146c4bd5_582ac2db5e6743b09bc3d116e76db957.jpg?x-oss-process=image/resize,m_fill,limit_0,s_300/quality,q_80">
                        <i class="fa fa-play-circle fa-3x"></i>
                    </div>
                    <div class="info">
                        <div class="name"><a href="musicList.jsp">一听沉醉,欧美抒情打动你心5</a></div>
                        <div class="author"><a href="#">music.猫</a> </div>
                        <div class="song-tags"><a href="#">欧美、抒情、节奏</a></div>
                    </div>
                </div>

            </div>
        </div> <!--推荐歌单-->
    </div>
</div> <!--轮播图下面的部分-->
<div class="play-bar navbar-fixed-bottom">  <!--播放器部分-->
    <div class="progress my-progress">
        <div class="progress-bar my-progress-bar" id="play-progress-bar" role="progressbar" aria-valuenow="60"
             aria-valuemin="0" aria-valuemax="100" style="width:0%;">
        </div>
    </div> <!--进度条-->
    <audio src="http://other.web.ra01.sycdn.kuwo.cn/resource/n1/128/59/30/3018701453.mp3" controls="controls" loop  id="audio-music" hidden></audio>
    <div class="handle" id="handle" style="margin-left:0%">
        <span class="current-time" id="current-time">00:00</span>
        <span>/</span>
        <span class="total-time" id="total-time">00:00</span>
    </div> <!--进度条拖拽块-->
    <div class="music">
        <img src="image/test.jpg">
        <div class="info">
            <span class="content">男孩</span><br>
            <span class="singer">梁博</span>
        </div>
        <div class="like">
            <a href="#"><i class=" fa fa-heart-o fa-2x"></i></a>
        </div>
    </div>     <!--左边的图片-->
    <div class="main-control">
        <div class="prev">
            <a><i class="fa fa-step-backward fa-2x"></i></a>
        </div>
        <div class="play-btn">
            <a><i class="fa fa-play-circle fa-4x" id="playBtn" ></i></a>
        </div>
        <div class="next">
            <a><i class="fa fa-step-forward fa-2x"></i></a>
        </div>
    </div> <!--中间的控制-->
    <div class="tunings">
        <div class="volume-control">
            <i class="fa fa-volume-up" style="float: left;"></i>
            <div class="progress volume-progress">
                <div class="progress-bar volume-bar" id="volume-bar" role="progressbar" aria-valuenow="60"
                     aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                </div>
            </div>
            <div class="volume-handle" id="volume-handle">

            </div>
        </div>
    </div>  <!--右边部分-->
</div> <!--底部播放器-->
<div class="modal fade " id="login-panel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog">
        <div class="modal-content">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style=" padding-left: 3px">
                    &times;
                </button>
                <div class="passport-movie"> <!--视频-->
                    <video loop autoplay src="image/a29ccbee1e9a1624832ef6d32c80225b.quicktime" id="login-video"></video>
                </div>
                <div class="passport-content"> <!--右边的内容-->
                    <div class="login" id="login">
                        <div class="agreement-title">
                            <span style="color: #ff410f;">账号登录</span>
                        </div>
                        <form action="" method="">
                            <div class="number">
                                <span>账号</span><br/>
                                <input type="text" id="username" name="username" placeholder="请输入账号" ><br/>
                            </div>
                            <div class="security">
                                <span style="margin-top: 30px;">密码</span><br/>
                                <input type="password" id="password" placeholder="请输入密码" name="password" class="btn"/>
                            </div>
                            <input type="button" class="next" id="loginBtn" value="登录" > <!--使用submit，用form表单提交-->
                        </form><br/>
                        <a href="#" style="margin-top: 115px;margin-left: 120px; display: block;" id="toregister">注册</a>
                    </div>    <!--登录板块-->
                    <div class="register" id="register" style="display: none;">  <!--注册板块-->
                        <div class="agreement-title">
                            <span>新用户注册</span>
                            <em>轻松两步即可完成注册</em>
                        </div>
                        <form>
                            <div class="number">
                                <span>手机号</span><br/>
                                <input type="text" name="usernumber" id="usernumber" placeholder="请输入手机号" > <span id="usernumberMsg"></span><br/>
                            </div>
                            <div class="security">
                                <span style="margin-top: 30px;">验证码</span><br/>
                                <input type="text" placeholder="请输入验证码" id="security-code" name="security-code" class="btn"/>
                                <input type="button" class="sendCode" value="发送验证码">
                            </div>
                        </form><br/>
                        <input type="button" class="next" value="下一步" id="next"> <!--使用button，用ajax提交，而不是用form表单-->
                        <a href="#" style="margin-top: 78px;margin-left: 120px; display: block;" id="backtoLogin">返回登录</a>
                    </div> <!--注册板块一-->
                    <div class="register1" id="register1" style="display: none;">
                        <div class="agreement-title">
                            <span>新用户注册</span>
                            <em>请输入6-12位密码</em>
                        </div>
                        <form action="<c:url value='/user/register'/> " method="post">
                            <div class="number">
                                <span>密码</span><br/>
                                <input type="password" id="regispassword" name="regispassword" placeholder="请输入密码" ><span></span><br/>
                            </div>
                            <div class="security">
                                <span style="margin-top: 30px;">重复密码</span><br/>
                                <input type="password" id="repassword" placeholder="再输入一次你设定的密码" name="repassword" class="btn"/><span></span>
                            </div>
                            <input type="submit" class="next" value="完成注册" id="complete"> <!--使用submit，用form表单提交-->
                        </form><br/>
                        <a href="#" style="margin-top: 115px;margin-left: 120px; display: block;" id="backtoLastRegis">返回上一步</a>
                    </div> <!--注册板块二-->
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div><!--登录面板-->



<div style="height: 1000px;"></div>
</body>
</html>