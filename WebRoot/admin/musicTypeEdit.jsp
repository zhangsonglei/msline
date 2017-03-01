<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.musicType!=null && #attr.musicType.music_type_id!=0">编辑</s:if><s:else>添加</s:else>音乐类别信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 $("#addBtn").bind('click',function(){
		if($("#paramsMusicType\\.music_type_name").val()==''){
			alert('类型名称不能为空');
			return;
		}
		$("#paramsMusicType\\.music_type_id").val(0);
		$("#info").attr('action','Admin_addMusicType.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 	if($("#paramsMusicType\\.music_type_name").val()==''){
				alert('类型名称不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveMusicType.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">音乐类别管理&gt;&gt;<s:if test="#attr.musicType!=null && #attr.musicType.music_type_id!=0">编辑</s:if><s:else>添加</s:else>音乐类别</span>
</div>
<form id="info" name="info" action="Admin_addMusicType.action" method="post">   
<s:hidden id="paramsMusicType.music_type_id" name="paramsMusicType.music_type_id" value="%{#attr.musicType.music_type_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.musicType!=null && #attr.musicType.music_type_id!=0">编辑</s:if><s:else>添加</s:else>音乐类别</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 类型名称：</td>
          <td width="65%">
          	<s:textfield name="paramsMusicType.music_type_name" id="paramsMusicType.music_type_name" value="%{#attr.musicType.music_type_name}"/>
          </td>
        </tr> 
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<s:if test="#attr.musicType!=null && #attr.musicType.music_type_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>