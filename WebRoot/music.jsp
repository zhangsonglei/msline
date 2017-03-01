<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>歌曲点播</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	var user_id="${userFront.user_id}";
	$("img[id^='play']").bind('click',function(){
		var music_id = $(this).attr("id").split("_")[1];
		window.location.href="page_queryMusic.action?paramsMusic.music_id="+music_id;
	});
	
	$("img[id^='down']").bind('click',function(){
		var music_id = $(this).attr("id").split("_")[1];
		if(user_id==""){
			alert("要下载歌曲，清先登录");
			return;
		}
		window.location.href="DownLoad.action?paramsMusic.music_id="+music_id;
	});
});
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<form name="info" id="info" action="page_listMusics.action" method="post" style="width:695px;height:100%">
	<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
	<div id="list">
		 <div class="nav">
		 	<div class="nav1">当前位置: 主页 > 歌曲点播  </div>
		 	<div class="nav2">　　　
		 	    音乐名称：
		      <input type="text" id="paramsMusic.music_name" name="paramsMusic.music_name" value="${paramsMusic.music_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
		            音乐类型：<s:select list="#attr.musicTypes" name="paramsMusic.music_type_id" value="%{#attr.paramsMusic.music_type_id}" cssClass="inputstyle"
				      		listKey="music_type_id" listValue="music_type_name" headerKey="0" headerValue="请选择" cssStyle="width:100px">
				      </s:select>&nbsp;&nbsp;
		 	<input type="button" value="搜索" onclick="serch();" class="btnstyle"/>
		 	</div>
		 </div>
		 <div class="list_info">
			<ul>
				<li>
					<div class="info_text1" style="font-weight:bold;font-size:14px"><img src="images/ico-1.gif" />&nbsp;&nbsp;音乐名称</div>
					<div class="info_text2" style="font-weight:bold;font-size:14px">音乐类型</div>
					<div class="info_text3" style="font-weight:bold;font-size:14px">演唱歌手</div>
					<div class="info_time" style="font-weight:bold;font-size:14px">上传时间</div>
				</li>
				<s:iterator value="#attr.musics" id="music">
				<li>
					<div class="info_text1">
					<img src="images/ico-1.gif" />&nbsp;&nbsp;<s:a href="page_queryMusic.action?paramsMusic.music_id=%{#music.music_id}" target="_blank" title="%{#music.music_title}"> 
						<s:property value="#music.music_name.length()>26?#music.music_name.substring(0,26)+'...':#music.music_name"/>
					</s:a>　
					</div>
					<div class="info_text2"><s:property value="#music.music_type_name"/></div>
					<div class="info_text3"><s:property value="#music.music_author"/></div>
					<div class="info_time">
						<s:property value="#music.music_date.substring(0,19)"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<img id="play_<s:property value='#music.music_id'/>" src="images/play.gif" title="点击播放" style="cursor:pointer"/>&nbsp;&nbsp;
						<%--<img id="down_<s:property value='#music.music_id'/>" src="images/down.gif" title="点击下载" style="cursor:pointer"/>--%>
					</div>
				</li>
				</s:iterator>
			</ul>
		 </div>
		 <jsp:include page="page.jsp"></jsp:include>
	</div>
	</form>
	<div id="Picture"></div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.info.action="page_listMusics.action";
	   document.info.submit();
	}
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  document.getElementById("pageNo").value=pagenum;
	  document.info.action="page_listMusics.action";
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{	
		document.getElementById("pageNo").value=pagenum;
		document.info.action="page_listMusics.action";
		document.info.submit();
	}
</script>
</body>
</html>