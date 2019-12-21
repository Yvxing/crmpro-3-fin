<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript">
        function dateStr (time) {
            var dateObj =time;
            var date = new Date(dateObj);
            var dateStr = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDay();
            return dateStr;
        }

        $(function () {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/baoxiao/getBaoxiaoByeid',
                dataType:'JSON',
                success:function(rs){
                    $(rs).each(function(index,item){
                        var bxt="";
                        var a="";
                        if(item.bxstatus==0){
                            bxt="未审批";
                            a="<a href=\"${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid='"+item.bxid+"'\">编辑</a>";
						}
                        if(item.bxstatus==1){
                            bxt="审批通过未付款";
                            a="<a href='#'>查看</a>";
                        }
                        if(item.bxstatus==2){
                            bxt="驳回";
                 		a="<a href=\"${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid='"+item.bxid+"'\">编辑</a>";
                        }
                        if(item.bxstatus==3){
                            bxt="审批通过已付款";
                      }
						var tr="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                            "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+item.bxid+"\" class=\"np\"></td>\n" +
                            "\t<td>"+item.bxid+"</td>\n"+
                            "\t<td>"+item.totalmoney+"</td>\n" +
                            "\t<td align=\"center\"><a href=''><u> "+item.employee.ename+" </u></a></td>\n" +
                            "\t<td align=\"center\"><a href=''><u> "+dateStr(item.bxtime)+" </u></a></td>\n" +
                            "\t<td>"+item.bxremark+"</td>\n" +
                            "\t\t<td>"+bxt+"</td>\n" +
                            "\t\t<td>"+a+"</td>\n" +
                            "</tr>";
                             $("#ttr").after(tr);

                    });
                }
            });

        });

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
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='/mybaoxiao-add.jsp';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="ttr">
	<td width="4%">选择</td>
	<td width="20%">编号</td>
	<td width="6%">总金额</td>
	<td width="6%">报销人</td>
	<td width="10%">使用时间</td>
	<td width="40%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

	
<%--
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>11</td>
	<td>123</td>
	<td align="center"><a href=''><u> 2019-09-09 </u></a></td>
	<td align="center"><a href=''><u> 2019-09-09 </u></a></td>
	<td>222222</td>
		<td>未审批</td>
		<td>审批通过</td>
		<td>驳回</td>
		<td>审批并付款</td>
		<td><a href="mybaoxiao-edit.jsp" >编辑</a> </td>
</tr>

--%>
	


<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>