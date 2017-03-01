<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线音乐播放</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<link href="js/jplayer/skin/blue.monday/jplayer.blue.monday.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jplayer/jquery.min.js"></script>
<script type="text/javascript" src="js/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="js/jplayer/lrc.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	var music_name = '<s:property value="#attr.music.music_name" />';
	var music_name2 = '<s:property value="#attr.music.music_name2" />';
	$("#jquery_jplayer_1").jPlayer({
		ready: function (event) {
			$(this).jPlayer("setMedia", { 
				title: "<span style='font-size:12px'>"+music_name+"</span>",
				mp3:"images/music/"+music_name2 //mp3的播放地址
			});
		},
		timeupdate: function(event) {
			if(event.jPlayer.status.currentTime==0){
				time = "";
			}else {
				time = event.jPlayer.status.currentTime;
			}
		},
		play: function(event) {
			//点击开始方法调用lrc。start歌词方法 返回时间time
			$.lrc.start($('#lrc_content').val(), function() {
				return time;
			});
		},
		ended:function(event){
			$("#lrc_list").removeAttr("style").html("<li>歌曲播放完毕！</li>");
		},
		swfPath: "js/jplayer",		//存放jplayer.swf的决定路径
		//solution:"html, flash", //支持的页面
		supplied: "mp3",		//支持的音频的格式
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		remainingDuration: true,
		toggleDuration: true
	});
	
	var music_id = "<s:property value='#attr.music.music_id' />";
	var music_price = "<s:property value='#attr.music.music_price' />";
	var user_id = "<s:property value='#attr.userFront.user_id' />";
	
	var maxHieght=Math.max(document.body.scrollHeight , document.documentElement.scrollHeight);
	$("#mask").height(maxHieght);
	$("#buy").bind("click",function(){
		if(user_id==""){
			alert("购买音乐清先登录");
			return false;
		}
		$("#hbzj").show();
		$("#mask").show();
	});
	
	$("#addBtn").bind("click",function(){
		var aQuery = {
			'paramsOrders.user_id':user_id,
			'paramsOrders.music_id':music_id,
			'paramsOrders.music_price':music_price
		};  
		$.post('page_addOrders.action',aQuery,
			function(responseObj) {
					$("#hbzj").hide();
					$("#mask").hide();
					if(responseObj.success) {	
						 alert('购买成功！');
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		},'json');
	});
	
	$("#cancelBtn").bind("click",function(){
		$("#hbzj").hide();
		$("#mask").hide();
	});
});
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }

ul, ol, dl {
	list-style: none;
}

.content {width: 402px;height:200px;overflow:hidden;padding:10px;background:#ccc;}
#lrc_list{margin:10px auto;background:#ccc;}
#lrc_list li{font:normal 14px/2.1 'microsoft yahei';text-align:center;}
#lrc_list li.hover {color:red;font-weight:bold;}

#mask{
	position:absolute;
	zIndex:1;
	width:100%;
	height:100%;
	top:0px;
	left:0px;
	background-color:#000;
	filter:alpha(opacity=40);
	opacity:0.40;
	display:none;
}
#hbzj{
	position:absolute;
	zIndex:999;
	width:300px;
	height:200px;
	top:260px;
	left:450px;
	border:5px solid #8a1e1e;
	background-color:white; 
	display:none;
}	
.regTable{
	margin-top:5px;
	margin-bottom:15px;
	width:99%;
	border-collapse: collapse;
}
.regTable td{
	padding:5px;
	border:1px solid #d2d2d8;
	height:30px;
	background-color:#ccf399;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="list" style="width:960px">
		 <div class="nav">当前位置: 在线音乐点播 > 在线音乐播放 >  </div>
		 <div class="article_title"><s:property value="#attr.music.music_name" /></div>
		 <div class="article_time" style="border:1px dashed #666">
		 	演唱歌手：<s:property value="#attr.music.music_author" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	点播次数： <s:property value="#attr.music.music_click" /> 次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	上传时间：<s:property value="#attr.music.music_date.substring(0,19)" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	售价：￥<s:property value="#attr.music.music_price" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 	[ <s:a id="buy" href="javascript:void(0)">我要购买</s:a> ]
		 </div>
		 <div class="article_pic">
		    <textarea id="lrc_content" name="textfield" cols="70" rows="10" style="display:none;"><pre><jsp:include page="images/music/${music.lrc_name}"></jsp:include></pre></textarea>
		 	<div id="jquery_jplayer_1" class="jp-jplayer" style="margin:0px auto"></div>
			<div id="jp_container_1" class="jp-audio" role="application" aria-label="media player" style="margin:0px auto">
			  <div class="jp-type-single">
			    <div class="jp-gui jp-interface">
			      <ul class="jp-controls">
					<button class="jp-play" role="button" tabindex="0">play</button>
					<button class="jp-stop" role="button" tabindex="0">stop</button> 
			      </ul>
			      <div class="jp-progress">
					<div class="jp-seek-bar">
						<div class="jp-play-bar"></div>
					</div>
				  </div>
			      <div class="jp-volume-controls">
					<button class="jp-mute" role="button" tabindex="0">mute</button>
					<button class="jp-volume-max" role="button" tabindex="0">max volume</button>
					<div class="jp-volume-bar">
						<div class="jp-volume-bar-value"></div>
					</div>
				  </div>
			      <div class="jp-time-holder">
					<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
					<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
					<div class="jp-toggles">
						<button class="jp-repeat" role="button" tabindex="0">repeat</button>
					</div>
				  </div>
			    </div>
			    <div class="jp-details">
					<div class="jp-title" aria-label="title">&nbsp;</div>
				</div>
			    <div class="jp-no-solution">
					<span>Update Required</span>
					To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
				</div>
			  </div>
			</div>
			<div class="content" style="margin:0px auto">
			    <ul id="lrc_list">
			    	<li>点击开始播放……</li>
			    </ul>
			</div>
		 
		 </div>
		 
		<div id="mask"></div>

		<div id="hbzj">
		<div style="width:100%;height:22px;background-color:#8a1e1e">
			<span id="MainTitle" style="color:white">确认订单信息</span>
		</div>
		<div style="width:100%;height:170px;overflow:scroll;">
		<table class="regTable" width="95%" align="center" cellpadding="0" cellspacing="0">
		    <tr>
				<td align="right" width="120">音乐名称：</td>
				<td align="left" width="*"><s:property value="#attr.music.music_name" /></td>
			</tr>
		    <tr>
				<td align="right">音乐售价：</td>
				<td align="left">
					￥<s:textfield value="%{#attr.music.music_price}" readonly="true" style="width:100px;" class="inputstyle"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="button" id="addBtn" class="btnstyle" value="确认购买"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="cancelBtn" class="btnstyle" value="取消"/>
				</td>
			</tr>
		</table>
		</div>
		</div>
		 
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>