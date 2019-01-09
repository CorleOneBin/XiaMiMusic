<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <link href="<c:url value='/css/reset.css' />" rel="stylesheet" />
    <link href="<c:url value='/css/myMusic.css'/> " rel="stylesheet" />
    <script src="<c:url value='/js/myMusic.js' />" type="text/javascript"></script>
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
                <li><a href="<c:url value='/index.jsp'/>">发现</a></li>
                <li class="active"><a href="<c:url value='/user/updateSessionUser'/> ">我的音乐</a></li>
                <li><a href="#">音乐人</a></li>
                <li><a href="#">客户端下载</a></li>
                <li><a href="#">会员中心</a></li>
                <li><a href="#">回旧版</a></li>
                <c:if test="${empty sessionScope.user}">
                    <li class="navbar-right login" data-toggle="modal" data-target="#login-panel">
                        <a href="#" id="toLoginPanel">登录/注册</a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li class="dropdown login">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                ${sessionScope.user.nickName}
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/editUser.jsp'/> ">个人中心</a></li>
                            <li><a href="<c:url value='/user/logOut'/> " onclick="alert('确定退出吗');">退出</a></li>
                        </ul>
                    </li>
                </c:if>
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
<div class="row my-row">
    <div class="col-sm-3">
        <div class="leftbar-inner">
            <div class="leftbar-content">
                <img src="image/test.jpg" class="img-circle">
                <div class="name">${sessionScope.user.nickName}</div>
                <div class="signature">${sessionScope.user.description}</div>
                <div class="description">来自宇宙深处的，2018年8月加入</div>
                <div class="info-count">
                    <div class="item">
                        <div class="item-count">27</div>
                        <div class="item-name">累计播放</div>
                    </div>
                    <div class="item">
                        <div class="item-count">0</div>
                        <div class="item-name">关注</div>
                    </div>
                    <div class="item">
                        <div class="item-count">0</div>
                        <div class="item-name">粉丝</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-9">
        <div class="rightbar-inner">
            <div class="rightbar-content">
                <div class="count-list">
                    <div class="count-item">
                        <div class="count-wrap">
                            <i class="fa fa-clock-o fa-3x"></i>
                            <div class="content">
                                <div class="count">55</div>
                                <div class="name"><a href="<c:url value='/music/toHistoryMusic?phoneNumber=${sessionScope.user.phoneNumber}'/> ">最近播放</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="count-item item-border">
                        <div class="count-wrap">
                            <i class="fa fa-heart-o fa-3x"></i>
                            <div class="content">
                                <div class="count">0</div>
                                <div class="name">我的收藏</div>
                            </div>
                        </div>
                    </div>
                    <div class="count-item">
                        <div class="count-wrap">
                            <i class="fa fa-music fa-3x"></i>
                            <div class="content">
                                <div class="count">0</div>
                                <div class="name">已购音乐</div>
                            </div>
                        </div>
                    </div>
                </div><!--最近播放，我的收藏，已购音乐-->
                <div class="collect-list">
                    <div class="block-title">
                        <h2>创建的歌单</h2>
                        <div class="external">
                            <a href="<c:url value='/category/toCreateCinfo' /> ">新建歌单</a>
                        </div>
                    </div>
                    <div class="adaptive-list">
                        <c:forEach items="${sessionScope.user.cinfos}" var="cinfos">
                            <div class="collect-item">
                                <img src="<c:url value='${cinfos.imgHref}'/> ">
                                <i class="fa fa-play-circle fa-3x" style="display: none;"></i>
                                <div class="name"><a href="<c:url value='/cinfo/toMusicList?cinfoId=${cinfos.id}'/>">${cinfos.name}(${cinfos.num})</a></div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> <!--中间部分-->
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
        <img src="<c:url value='/image/you.jpg'/>">
        <div class="info">
            <span class="content">一路上有你</span><br>
            <span class="singer">张学友</span>
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
                        <input type="submit" class="next" value="登录" > <!--使用submit，用form表单提交-->
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
                            <input type="text" name="usernumber" id="usernumber" placeholder="请输入手机号" ><br/>
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
                    <form action="" method="">
                        <div class="number">
                            <span>密码</span><br/>
                            <input type="password" id="regispassword" name="regispassword" placeholder="请输入密码" ><br/>
                        </div>
                        <div class="security">
                            <span style="margin-top: 30px;">重复密码</span><br/>
                            <input type="password" id="repassword" placeholder="再输入一次你设定的密码" name="repassword" class="btn"/>
                        </div>
                        <input type="submit" class="next" value="完成注册" id="complete"> <!--使用submit，用form表单提交-->
                    </form><br/>
                    <a href="#" style="margin-top: 115px;margin-left: 120px; display: block;" id="backtoLastRegis">返回上一步</a>
                </div> <!--注册板块二-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div><!--登录面板-->
</body>
</html>