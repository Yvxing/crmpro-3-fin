<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>档案信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

	<script type="text/javascript">
        $(function () {
            $("#selAll").click(function () {
                $("input:checkbox").prop("checked",true)
            })
            $("#reverse").click(function () {
                $("input:checkbox").each(function () {
                    this.checked=!this.checked
                })
            })
			$("#disAll").click(function () {
				$("input:checkbox").removeAttr("checked")
            })
			$("#del").click(function () {
			    var ids = [];
			    $(".np:checked").each(function () {
			        var id = $(this).val();
			        ids.push(id);
                })
				$.ajax({
					type:'post',
					url:'${pageContext.request.contextPath}/archives/del',
					dataType:'text',
					success:function (data) {
						if (data=="true"){
						    alert("successful delete")
                        }
                    }
				})

            })

			$("#daochu").click(function () {
			    var ids = [];
			    $(".np:checked").each(function () {
					var id=$(this).val()
					ids.push(id)
                })
				$.ajax({
					type:'post',
					url:'${pageContext.request.contextPath}/archives/daochu',
					dataType:'text',
					success:function (data) {
						if (data == "true") {
						    alert("find file in local  url:         .")
                        }
                    }
				})

            })
        })

	</script>

	<script type="text/javascript">
		$(function () {
		    $.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/archives/getAll',
				dataType:'json',
				success:function (data) {
					$(data).each(function (index, item) {
                        var tr ="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                            "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+item.dnum+"\" class=\"np\"></td>\n" +
                            "\t<td>"+item.dnum+"</td>\n" +
                            "\t<td>"+item.employee.ename+"</td>\n" +
                            "\t<td>"+item.employee.eage+"</td>\n" +
                            "\t<td>"+item.school+"</td>\n" +
                            "\t<td>"+item.hirdate+"</td>\n" +
                            "\t<td>"+item.landline+"</td>\n" +
                            "\t<td>"+item.xueli+"</td>\n" +
                            "\t<td>"+item.email+"</td>\n" +
                            "\t<td>"+item.zzmm+"</td>\n" +
                            "\t<td>"+item.minzu +"</td>\n" +
                            "\t<td><a href=\"#\">编辑</a> | <a href=\"#\">查看详情</a></td>\n" +
                            "</tr>";
                        $("#ttr").after(tr);
                    })

                }

			})

        })
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
    当前位置:项目管理>>档案信息管理
 </td>

 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;员工档案信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="ttr">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">姓名</td>
	<td width="10%">年龄</td>
	<td width="10%">毕业院校</td>
	<td width="8%">入职时间</td>
	<td width="5%">联系方式</td>
	<td width="8%">学历</td>
	<td width="6%">邮箱</td>
	<td width="8%">政治面貌</td>
	<td width="8%">民族</td>	
	<td width="10%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >--%>
	<%--<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>--%>
	<%--<td>1</td>--%>
	<%--<td align="left"><a href=''><u>农业银行自助管理系统</u></a></td>--%>
	<%--<td>中国农业银行</td>--%>
	<%--<td>张云</td>--%>
	<%--<td>苏鑫超</td>--%>
	<%--<td>6</td>--%>
	<%--<td>2015-01-03</td>--%>
	<%--<td>2015-02-03</td>--%>
	<%--<td>2015-06-03</td>--%>
	<%--<td>进行中</td>--%>
	<%--<td><a href="project-base-edit.jsp">编辑</a> | <a href="project-base-look.jsp">查看详情</a></td>--%>
<%--</tr>--%>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a id="selAll" class="coolbg">全选</a>
	<a id="disAll" class="coolbg">全不选</a>
	<a id="reverse"  class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a id="del"  class="coolbg">&nbsp;删除&nbsp;</a>
	<a id="daochu" class="coolbg">&nbsp;导出Excel&nbsp;</a>
	<a href="archives-add.jsp" class="coolbg">&nbsp;添加档案信息&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>