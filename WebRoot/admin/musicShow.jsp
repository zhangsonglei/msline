<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音乐信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listMusics.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的音乐！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delMusics.action?paramsMusic.ids="+ids;
       document.info.submit();
    }
    
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
  document.info.action="Admin_listMusics.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listMusics.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">音乐管理&gt;&gt;音乐查询</span>
</div>
<form name="info" id="info" action="Admin_listMusics.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">音乐列表</td>
    <td width="" align="right">
            音乐名称：
      <input type="text" id="paramsMusic.music_name" name="paramsMusic.music_name" value="${paramsMusic.music_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
            演唱歌手：
      <input type="text" id="paramsMusic.music_author" name="paramsMusic.music_author" value="${paramsMusic.music_author}" class="inputstyle" Style="width:100px;"/>&nbsp;
            音乐类型：
      <s:select id="paramsMusic.music_type_id" name="paramsMusic.music_type_id" value="%{#attr.paramsMusic.music_type_id}" 
      		list="#attr.musicTypes" listKey="music_type_id" listValue="music_type_name" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">音乐名称</td>
     <td width="" align="center">演唱歌手</td>
     <td width="" align="center">音乐类型</td>
     <td width="" align="center">点播次数</td>
     <td width="" align="center">添加时间</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.musics!=null && #attr.musics.size()>0">
   <s:iterator value="#attr.musics" id="music" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#music.music_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#music.music_name"/></td>
     <td width="" align="center"><s:property value="#music.music_author"/></td>
     <td width="" align="center"><s:property value="#music.music_type_name"/></td>
     <td width="" align="center"><s:property value="#music.music_click"/></td>
     <td width="" align="center"><s:property value="#music.music_date.substring(0,19)"/></td>
     <td width="" align="center">
       <%--<s:a href="Admin_queryMusic.action?paramsMusic.music_id=%{#music.music_id}">查看</s:a>--%>&nbsp;
       <s:a href="Admin_editMusic.action?paramsMusic.music_id=%{#music.music_id}">编辑</s:a>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="9" align="center"><b>&lt;不存在音乐信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>