<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>创建任务</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="application/javascript">
		<%--加载所有项目--%>
        $(function(){
            $.ajax({
                type: 'GET',
                url: '${pageContext.request.contextPath}/project/allPro',
                dataType: 'json',
                success: function (resultData) {
                    $(resultData).each(function (index, item) {
                        var option = "<option value=" + item.project.pid + ">" + item.project.pname + "</option>";
                        $("#pro").append(option);
                    });
                },
            });


			//任务执行者
            $.ajax({
                type: 'GET',
                url: '${pageContext.request.contextPath}/project/allPro',
                dataType: 'json',
                success: function (resultData) {
                    $(resultData).each(function (index, item) {
                        var option = "<option value=" + item.employee.eid + ">" + item.employee.ename + "</option>";
                        $("#emp").append(option);
                    });
                },
            });
            //任务分配者
            $.ajax({
				type:'get',
				url:'${pageContext.request.contextPath}/task/allEmps',
				dataType:'json',
				success:function (data) {
					$(data).each(function (index, item) {
						var option = "<option value="+item.eid+">"+item.ename+"</option>";
						$("#femp").append(option);
                    })
                }
			});



        });
		//选择项目后的功能    第一栏的数据
        function addayalisys(obj){
            //查询需求  查询当前需求下模块
            $.ajax({
                type: 'GET',
                url: '${pageContext.request.contextPath}/analysis/totask',
                data: {"id": obj},
                dataType: 'json',
                success: function (resultData) {
                    // 需求
                    $("#anid").val(resultData.analysis.title);
                    //清楚从第二option开始的选项
                    $("option[class='opt']").remove();
                    //填充模块
                    $(resultData.modules).each(function(index,item){
                        var option = "<option class='opt' value=" + item.id + ">" + item.modname + "</option>";
                        $("#mod").append(option);
                    });

                },
            });
        }

        //对功能的回传   当第三个选项选择后会调用该方法onchange
        function addfunc(obj){
            $.ajax({
                type: 'GET',
                url: '${pageContext.request.contextPath}/function/findFsByAid',
				data:{"mid":obj},
                dataType: 'json',
                success: function (resultData) {
                     $("option[name='opt']").remove();
                    $(resultData).each(function (index, item) {
                        var option = "<option name='opt' value=" + item.id + ">" + item.functionname + "</option>";
                        $("#fun").append(option);
                    });
                },
            });
		}


       function commit(){
            $("#form7").submit();
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
    当前位置:任务管理>>创建任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form7" action="${pageContext.request.contextPath}/task/saveTask" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;创建任务&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="pro" onchange="addayalisys(this.value)">
			<option value="" disabled selected hidden>请选择项目</option>
		</select>-
		<input type="text" name="" id="anid">
		-<select id="mod" onchange="addfunc(this.value)">
		<option value="" disabled selected hidden>请选择模块</option>
	    </select>-
		<select id="fun" name="funFk">
			<option value="" disabled selected hidden>请选择功能</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="tasktitle"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input type="date" name="start"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input type="date" name="end"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="emp" name="empFk2">
			<option  value="" disabled selected hidden>任务的执行者</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">分配者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="femp" name="empFk">
			<option value="" disabled selected hidden>任务的分配者</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level"><option value="高">高</option><option value="中">中</option><option value="低">低</option></select></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>