<%@ page language="java"  import="com.ujiuye.pojo.Baoxiao,java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加附件</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

	<script type="application/javascript">
        function dateStr(time) {
            var date = new Date(time);
            var dateStr = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate();
            return dateStr;
        }
        $(function () {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/baoxiao/showBaoxiaoAndAllExpendituretype',
                data:{"bxid":${param.bxid}},
                dataType:'json',
                success:function(rs) {
                    $("#totalmoney").val(rs.totalmoney);
                    $("#bxtime").val(dateStr(rs.bxtime));
                    $("#bxremark").val(rs.bxremark);
                    $("#bxid").val(rs.bxid);
                    // 获取所有的支付方式
					$(rs.expendituretypes).each(function(index,item){
                        var option ="";
						if(rs.paymode == item.etid){
                            option ="<option selected value="+item.etid+">"+item.etname+"</option>";
						}else{
                            option ="<option  value="+item.etid+">"+item.etname+"</option>";
						}
						$("#paymode").append(option);
					});

                    if(rs.baoxiaoreplies.length>0){
                        $("#lspz").css("display","table-row");
                        //循环遍历我们报销单的审批信息
                        var info ="";
                        $(rs.baoxiaoreplies).each(function(index,item){
                            var date = new Date(item.replytime);
                            var time= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+": "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();

                            info+="审批时间:     "+time+":"+"          审批意见:      "+item.content+"\n";
                        });
                        $("#pizhu").append(info);
                    }
                },
            });
        });

        function commit(){
            $("#form11").submit();
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
    当前位置:报销管理>>添加新的报销
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form11" action="${pageContext.request.contextPath}/baoxiao/updateBaoxiao" method="post" >
     <input type="hidden" id="bxid" name="bxid" >
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加新的报销单&nbsp;</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">支出类型：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">

		<select name="paymode" id="paymode">

		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">总金额：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="26"  id="totalmoney" name="totalmoney"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">使用时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="date" size="52" name="durtime" id="bxtime"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130  id="bxremark" name="bxremark"></textarea>
	</td>
</tr>
    <tr >
		<td align="right" bgcolor="#FAFAF1" >批注：</td>
		<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
			<textarea rows=3 cols=130 id="pizhu" readonly="true"></textarea>
		</td>
	</tr>

<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="/mybaoxiao-base.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>