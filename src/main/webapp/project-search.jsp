<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

    <%--<script type="text/javascript">
        function datestr(time) {
            // var dateObj = JSON.parse(time);
            console.log(time)
            var date = new Date(time);
            var dateStr = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate();
            return dateStr;
        }
    </script>--%>

  <%--  <script type="text/javascript">
          $(function(){
              $.ajax({
                  type:'GET',
                  url:'/project/search',
                  data:
                  dataType:'json',
                  success:function(rs){
                      $(rs).each(function(index,item){
                            // var datestr = function (time) {
                            //         var dateObj = JSON.parse(time);
                            //         var date = new Date(dateObj);
                            //         var dateStr = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate();
                            //         return dateStr;
                            // }
                          var tr ="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                              "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\"101\" class=\"np\"></td>\n" +
                              "\t<td>"+item.project.pid+"</td>\n" +
                              "\t<td align=\"left\"><a href=''><u>"+item.project.pname+"</u></a></td>\n" +
                              "\t<td>"+item.customer.comname+"</td>\n" +
                              "\t<td>"+item.customer.companyperson+"</td>\n" +
                              "\t<td>"+item.employee.ename+"</td>\n" +
                              "\t<td>"+item.project.empcount+"</td>\n" +
                              "\t<td>"+datestr(item.project.starttime)+"</td>\n" +
                              "\t<td>"+datestr(item.project.buildtime)+"</td>\n" +
                              "\t<td>"+datestr(item.project.endtime)+"</td>\n" +
                              "\t<td>进行中</td>\n" +
                              "\t<td><a href=\"/project/findOne?pid="+item.project.pid+"\">编辑</a> | <a href=\"/project/showDetail?pid="+item.project.pid+"\">查看详情</a></td>\n" +
                              "</tr>";
                               $("#ttr").after(tr);

                      });

                  }
              });
          });
    </script>--%>


</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='/project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='/project-base.jsp' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150'>
            <option value="" disabled selected hidden>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='2'>项目经理</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value="" disabled selected hidden>排序...</option>
            <option value='1'>立项时间</option>
            <option value='2'>计划完成时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="1" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="ttr">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="6%">立项时间</td>
	<td width="8%">最近更新时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>
<c:forEach varStatus="pro" var="item" items="${item}">
     <tr>
            <td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
            <td>${item.project.pid}</td>
            <td align="left">
                <a href=''><u>${item.project.pname}</u></a>
            </td>
            <td>${item.customer.comname}</td>
            <td>${item.customer.companyperson}</td>
            <td>${item.employee.ename}</td>
            <td>${item.project.empcount}</td>
            <td>${item.project.starttime}</td>
            <td>${item.project.buildtime}</td>
            <td>${item.project.endtime}</td>
            <td>进行中</td>
            <td>
                <a href="/project/findOne?pid=${item.project.pid}">编辑</a>
                <a href="/project/showDetail?pid=${item.project.pid}">查看详情</a>
            </td>
    </tr>
</c:forEach>


<%--
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td align="left"><a href=''><u>农业银行自助管理系统</u></a></td>
	<td>中国农业银行</td>
	<td>张云</td>
	<td>苏鑫超</td>
	<td>6</td>
	<td>2015-01-03</td>
	<td>2015-02-03</td>
	<td>2015-06-03</td>
	<td>进行中</td>
	<td><a href="project-base-edit.jsp">编辑</a> | <a href="project-base-look.jsp">查看详情</a></td>
</tr>
--%>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
    <a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>