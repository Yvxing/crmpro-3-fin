<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的任务信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="application/javascript">
        function sall(){
            $("input:checkbox").prop("checked",true);
        }
        function igAll() {
            $("input:checkbox").removeAttr("checked");
        }
        function reverse() {
            $("input:checkbox").each(function () {
                this.checked = !this.checked
            })
        }
        function del() {
            var ids = [];
            $(".np:checked").each(function () {
                var id = $(this).val();
                ids.push(id);
            })
            $.ajax({

            })
        }
        function daochu() {
            var ids = [];
            $(".np:checked").each(function () {
                var id = $(this).val();
                ids.push(id);
            })
            $.ajax({

            })
        }
        // 时间转换
        function dateStr(time) {
            var date = new Date(time);
            var dateStr = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate();
            console.log(date.getDate())
            return dateStr;
        }
        $(function(){
            taskinfo();
        });
        function taskinfo(){
            $.ajax({
                type:'GET',
                url:'${pageContext.request.contextPath}/emp/getOneEmpAndTask',
                dataType:'json',
                success:function(rs){
                    $("tr[name='ajaxtr']").remove();

                    $(rs.tasks).each(function(index,item){
                        var status = "";
                        var a= "";
                        if(item.status==0){
                            status="任务未接受";
                            a="<a href='javascript:updatestatus1("+item.id+")'>开始任务</a>|";
                        }
                        if(item.status==1){
                            status = "任务进行中...";
                            a="<a href='javascript:updatestatus2("+item.id+")'>提交任务</a>|";
                        }
                        if(item.status==2){
                            status = "任务已提交";
                        }
                        var tr ="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                            "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+item.id+"\" class=\"np\"></td>\n" +
                            "\t<td>"+item.id+"</td>\n" +
                            "\t<td>"+item.tasktitle+"</td>\n" +
                            "\t<td align=\"center\">"+rs.employee.ename+"</td>\n" +
                            "\t<td align=\"center\">"+status+"</td>\n" +
                            "\t<td align=\"center\">"+item.level+"</td>\n" +
                            "\t<td>"+dateStr(item.starttime)+"</td>\n" +
                            "\t<td>"+dateStr(item.endtime)+"</td>\n" +

                            "\t<td>"+a+"<a href=\"/task-look.jsp\">查看详情</a></td>\n" +
                            "</tr>";

                        $("#ttr").after(tr);

                    });
                }
            });
        }
        // 处理接收任务的
        function updatestatus1(obj){
             $.ajax({
                 type:'GET',
                 url:'${pageContext.request.contextPath}/task/updateTaskStatus',
                 data:{"id":obj,"status":1},
                 dataType:'text',
                 success:function(rs) {
                      if(rs=="true"){
                           window.location.href="${pageContext.request.contextPath}/task-my.jsp";
                      }else {
                          alert("something wrong")
                      }
                 }
             });
        }
        //处理提交任务
        function updatestatus2(obj){
            $.ajax({
                type:'GET',
                url:'${pageContext.request.contextPath}/task/updateTaskStatus',
                data:{"id":obj,"status":2},
                dataType:'text',
                success:function(rs) {
                    if(rs=="true"){
                        window.location.href="${pageContext.request.contextPath}/task-my.jsp";
                    }else{
                        alert("something wrong")
                    }
                }
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
    当前位置:任务管理>>我的任务信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>

<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150'>
          <option value='0'>选择类型...</option>
          	<option value='1'>任务标题</option>
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
            <option value='id'>排序...</option>
            <option value='pubdate'>开始时间</option>
            <option value='pubdate'>结束时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;我的任务信息&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="ttr">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">任务标题</td>
	<td width="10%">执行者</td>
	<td width="8%">状态</td>
	<td width="8%">优先级</td>
	<td width="8%">开始时间</td>
	<td width="8%">结束时间</td>
	<td width="15%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>完成用户信息增删改查</td>
	<td align="center">某某某</td>
	<td align="center">进行中...</td>
	<td align="center">高</td>
	<td>2015-02-03</td>
	<td>2015-02-15</td>
	<td><a >开始任务</a> | <a >任务完成</a> | <a href="task-look.html">查看详情</a></td>
</tr>--%>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a  onclick="sall()" class="coolbg">全选</a>
	<a  onclick="igAll()" class="coolbg">全不选</a>
	<a onclick="reverse()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a onclick="del()"  class="coolbg">&nbsp;删除&nbsp;</a>
	<a onclick="daochu()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>