<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的音乐订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
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
	<jsp:include page="leftMenu.jsp"></jsp:include>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  我的音乐订单</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_listMyOrderss.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>    
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
					     <td colspan="9" align="right">
					     	音乐名称：
      						<input type="text" id="paramsOrders.music_name" name="paramsOrders.music_name" value="${paramsOrders.music_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
					     	<input type="button" value="搜索" onclick="serch();" class="btnstyle"/>
					     </td>
					</tr>
					<tr class="head_text">
					     <td width="" align="center">音乐名称</td>
					     <td width="" align="center">音乐类型</td>
					     <td width="" align="center">购买价格</td>
					     <td width="" align="center">购买时间</td>
					     <td width="80" align="center">试听/下载</td>
					</tr>
					<s:if test="#attr.orderss!=null && #attr.orderss.size()>0">
					   <s:iterator value="#attr.orderss" id="orders" status="status">
					   <tr> 
					     <td width="" align="center"><s:property value="#orders.music_name"/></td>
					     <td width="" align="center"><s:property value="#orders.music_type_name"/></td>
					     <td width="" align="center"><s:property value="#orders.music_price"/></td>
					     <td width="" align="center"><s:property value="#orders.orders_date.substring(0,19)"/></td>
					     <td width="" align="center">
					      <img id="play_<s:property value='#orders.music_id'/>" src="images/play.gif" title="点击播放" style="cursor:pointer"/>&nbsp;&nbsp;
						  <img id="down_<s:property value='#orders.music_id'/>" src="images/down.gif" title="点击下载" style="cursor:pointer"/>
					     </td>
					   </tr> 
					   </s:iterator>
					</s:if>
				    <s:else>
				    <tr>
				      <td height="60" colspan="9" align="center"><b>&lt;不存在音乐订单信息&gt;</b></td>
				    </tr>
				    </s:else>
				 </table>
				 </form>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.info.action="page_listMyOrderss.action";
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
	  document.info.action="page_listMyOrderss.action";
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{
		document.getElementById("pageNo").value=pagenum;
		document.info.action="page_listMyOrderss.action";
		document.info.submit();
	}
	$(document).ready(function(){
		$("img[id^='play']").bind('click',function(){
			var music_id = $(this).attr("id").split("_")[1];
			window.location.href="page_queryMusic.action?paramsMusic.music_id="+music_id;
		});
		
		$("img[id^='down']").bind('click',function(){
			var music_id = $(this).attr("id").split("_")[1];
			window.location.href="DownLoad.action?paramsMusic.music_id="+music_id;
		});
		
	});
</script>
</body>
</html>