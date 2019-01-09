﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<c:url value='/admin/static/h-ui/css/H-ui.min.css'/> " />
<link rel="stylesheet" type="text/css" href="<c:url value='/admin/static/h-ui.admin/css/H-ui.admin.css'/> " />
<link rel="stylesheet" type="text/css" href="<c:url value='/admin/lib/Hui-iconfont/1.0.8/iconfont.css'/> " />
<link rel="stylesheet" type="text/css" href="<c:url value='/admin/static/h-ui.admin/skin/default/skin.css'/> " id="skin" />
<link rel="stylesheet" type="text/css" href="<c:url value='/admin/static/h-ui.admin/css/style.css'/> " />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>音乐管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 音乐中心 <span class="c-gray en">&gt;</span> 音乐管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <%--<div class="text-c">
        <input type="text" class="input-text" style="width:250px" placeholder="输入音乐名称、歌手、专辑" id="" name="">
        <button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
    </div>--%>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="member_add('添加音乐','<c:url value="/cinfo/toAddMusic"/> ','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加音乐</a></span> <span class="r">共有数据：<strong>${count}</strong> 条</span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th width="25"><input type="checkbox" name="" value=""></th>
                    <th width="80">ID</th>
                    <th width="100">歌曲名</th>
                    <th width="40">歌手</th>
                    <th width="90">专辑</th>
                    <th width="150">mp3</th>
                    <th width="80">浏览次数</th>
                    <th width="70">状态</th>
                    <th width="100">操作</th>
                </tr>
		    </thead>
		    <tbody>
            <c:forEach items="${musics}" var="music" varStatus="status">
                <tr class="text-c">
                    <td><input type="checkbox" value="${music.id}" name="musicId"></td>
                    <td>${status.index+1}</td>
                    <td><u style="cursor:pointer" class="text-primary" onclick="member_show('张三','member-show.html','10001','360','400')">${music.name}</u></td>
                    <td>${music.songer}</td>
                    <td>${music.album}</td>
                    <td>${music.mp3}</td>
                    <td class="text-c">0</td>
                    <td class="td-status"><span class="label label-success radius">已上架</span></td>
                    <td class="td-manage">
                        <a style="text-decoration:none" onClick="member_stop(this,'10001')" href="javascript:;" title="停用">
                            <i class="Hui-iconfont">&#xe631;</i>
                        </a>
                        <a title="编辑" href="javascript:;" onclick="member_edit('编辑','<c:url value="/music/toEditPage?musicId=${music.id}&name=${music.name}&songer=${music.songer}&album=${music.album}"/> ','4','','510')" class="ml-5" style="text-decoration:none">
                            <i class="Hui-iconfont">&#xe6df;</i>
                        </a>
                        <a title="删除" href="javascript:;" onclick="member_del(this,'${music.id}')" class="ml-5" style="text-decoration:none">
                            <i class="Hui-iconfont">&#xe6e2;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
		    </tbody>
	    </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<c:url value='/admin/lib/jquery/1.9.1/jquery.min.js'/> "></script>
<script type="text/javascript" src="<c:url value='/admin/lib/layer/2.4/layer.js'/> "></script>
<script type="text/javascript" src="<c:url value='/admin/static/h-ui/js/H-ui.min.js'/> "></script>
<script type="text/javascript" src="<c:url value='/admin/static/h-ui.admin/js/H-ui.admin.js'/> "></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<c:url value='/admin/lib/My97DatePicker/4.8/WdatePicker.js'/> "></script>
<script type="text/javascript" src="<c:url value='/admin/lib/datatables/1.10.0/jquery.dataTables.min.js'/> "></script>
<script type="text/javascript" src="<c:url value='/admin/lib/laypage/1.2/laypage.js'/> "></script>
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,7,8]}// 制定列不参与排序
		]
	});

});
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
        $.ajax({
            type: 'POST',
			url: '<c:url value="/music/soldOut"/> ',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="上架"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
				$(obj).remove();
				layer.msg('已下架!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '<c:url value="/music/deleteMusic"/> ',
            data:{musicId:id},
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*批量删除*/
function datadel(){
    layer.confirm('确认要全部删除吗？',function(index){
        /*获取选中的id*/
        obj = document.getElementsByName("musicId");
        musicId = [];
        for(k in obj){
            if(obj[k].checked)
                musicId.push(obj[k].value);
        }
        $.ajax({
            type: 'POST',
            url: '<c:url value="/music/delteBatchMusic"/> ',
            traditional:true,
            data:{musicId:musicId},
            success: function(data){
                for(var i = 0 ; i < obj.length; i++){
                    if(obj[i].checked){
                        $(obj[i]).parents("tr").remove();
                    }
                }
                layer.msg('已删除!',{icon:1,time:1000});
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });
}
</script>
</body>
</html>