<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript">
		 function checkPass(obj){
           $.ajax({
                  type: 'GET',
                  url: '${pageContext.request.contextPath}/emp/checkPass',
                  data: {"password":obj},
                  dataType: 'json',
                  success: function (rs) {
                      console.info(rs.flag);
					   if(rs.flag){
                           $("#div").hide();
					   }else{
                           $("#div").show();

                           $("#div").html("<font color='red'>原始密码输入错误</font>");
                           $("#yma").val("");
					   }
                  },
			  });
		 }

		 function checkRePass(obj){
              var np= $("#np").val();
              if(obj === np){
                   $("#pdiv").hide();
			  }else{
                  $("#pdiv").show();
                  $("#pdiv").html("<font color='red'>两次输入密码不一致</font>");
                  $("#cp").val("");
			  }
		 }
		 function commit(){
             $.ajax({
                 type: 'GET',
                 url: '${pageContext.request.contextPath}/emp/modifyPass',
                 data: {"password":$("#np").val()},
                 dataType: 'json',
                 success: function (rs) {
                     if(rs.flag){
                         /*
                          "window.location.href"、"location.href"是本页面跳转
                           "parent.location.href"是上一层页面跳转
                           "top.location.href"是最外层的页面跳转
                         */
                         top.location.href="${pageContext.request.contextPath}/emp/logout";
					 }
                 },
             });
		 }

	</script>

</head>


<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:我的信息>>修改密码
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form22" id="form22" action="${pageContext.request.contextPath}/emp/modifyPass" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;修改密码&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">原密码：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="yma" onblur="checkPass(this.value)"/>
	<div id="div"></div>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">新密码：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="password" id="np"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">重复密码：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="repassword" id="cp" onblur="checkRePass(this.value)"/>
	 <div id="pdiv"></div>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit();" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>


</body>
</html>