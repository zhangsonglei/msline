<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.music!=null && #attr.music.music_id!=0">编辑</s:if><s:else>添加</s:else>音乐信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		//KE.sync('noticeContent');
		if($("#paramsMusic\\.music_name2").val()==''){
			alert('音乐文件不能为空');
			return;
		}
		if($("#paramsMusic\\.lrc_name").val()==''){
			alert('音乐歌词不能为空');
			return;
		}
		if($("#paramsMusic\\.music_name").val()==''){
			alert('音乐名称不能为空');
			return;
		}
		if($("#paramsMusic\\.music_author").val()==''){
			alert('演唱歌手不能为空');
			return;
		}
		if($("#paramsMusic\\.music_type_id").val()=='0'){
			alert('音乐类型不能为空');
			return;
		}
		$("#paramsMusic\\.music_id").val(0);
		$("#info").attr('action','Admin_addMusic.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		    //KE.sync('noticeContent');
		 if($("#paramsMusic\\.music_name2").val()==''){
				alert('音乐文件不能为空');
				return;
			}
			if($("#paramsMusic\\.lrc_name").val()==''){
				alert('音乐歌词不能为空');
				return;
			}
			if($("#paramsMusic\\.music_name").val()==''){
				alert('音乐名称不能为空');
				return;
			}
			if($("#paramsMusic\\.music_author").val()==''){
				alert('演唱歌手不能为空');
				return;
			}
			if($("#paramsMusic\\.music_type_id").val()=='0'){
				alert('音乐类型不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveMusic.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">音乐管理&gt;&gt;<s:if test="#attr.music!=null && #attr.music.music_id!=0">编辑</s:if><s:else>添加</s:else>音乐</span>
</div>
<form id="info" name="info" action="Admin_addMusic.action" method="post">   
<s:hidden id="paramsMusic.music_id" name="paramsMusic.music_id" value="%{#attr.music.music_id}" /> 
<input type="hidden" name="paramsMusic.music_name2" id="paramsMusic.music_name2" value='<s:property value="#attr.music.music_name2"/>'/>
<input type="hidden" name="paramsMusic.lrc_name" id="paramsMusic.lrc_name" value='<s:property value="#attr.music.lrc_name"/>'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.music!=null && #attr.music.music_id!=0">编辑</s:if><s:else>添加</s:else>音乐</TD>
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
		  <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 音乐文件：</td>
		  <td width="65%" align="left" colspan="3">
		    <span id="userImg"><s:property value="#attr.music.music_name2"/></span>
		    <span id="op" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
	      </td>
	    </tr>
	     <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left" colspan="3">
	          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
	    <tr>
		  <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 音乐歌词：</td>
		  <td width="65%" align="left" colspan="3">
		    <span id="userImg2"><s:property value="#attr.music.lrc_name"/></span>
		    <span id="op2" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
	      </td>
	    </tr>
	     <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left" colspan="3">
	          <iframe name="uploadPage" src="uploadImg2.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 音乐名称：</td>
          <td width="65%">
          	<s:textfield name="paramsMusic.music_name" id="paramsMusic.music_name" value="%{#attr.music.music_name}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 音乐类型：</td>
          <td width="65%">
          	<s:select id="paramsMusic.music_type_id" name="paramsMusic.music_type_id" value="%{#attr.music.music_type_id}" 
	      		list="#attr.musicTypes" listKey="music_type_id" listValue="music_type_name" 
	      		class="selectstyle" cssStyle="width:150px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 演唱歌手：</td>
          <td width="65%">
          	<s:textfield name="paramsMusic.music_author" id="paramsMusic.music_author" value="%{#attr.music.music_author}"/>
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
          	<s:if test="#attr.music!=null && #attr.music.music_id!=0">
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
<script>        
	   KE.show({ 
	            id : 'noticeContent',
	            items:['plainpaste', '|', 'selectall', 'bold','italic'],
	            resizeMode:1
	            
	                    
	   });
	   
</script>
</body>
</html>