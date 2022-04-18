<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">

	$(function(){
		//给“创建”按钮添加单击事件
		$("#createStudentBtn").click(function () {
            //初始化工作
            //在意js代码
			$("#createStudentForm").get(0).reset();
			//弹出创建市场活动的模态窗口
			$("#createStudentModal").modal("show");
		});
		//给保存按钮添加单击事件
		$("#saveCreateStudentBtn").click(function () {
			//收集参数
			var owner=$("#create-marketStudentOwner").val();
			var name=$.trim($("#create-marketStudentName").val());
			var age=$.trim($("#create-age").val());
			var email=$.trim($("#create-email").val());
			//表单验证
			if (owner==""){
				alert("所有者不能为空");
				return;

			}
			if (name==""){
				alert("姓名不能为空");
				return;
			}
			/*
			  正则表达式：
			   1.语言、语法：定义字符串的匹配模式，可以用来判断指定的具体字符串是否符合字符串的匹配模式。
			   2.语法通则：
			     1）//：在js中定义一个正则表达式，var refExp=/...../;
			     2)^:匹配字符串的开头位置
			       $：匹配字符串的结尾
			     3）[]:匹配指定字符集中的一位字符，var regExp=/[abc]/;
			                                    var regExp=/[a-z0-9]/
			     4){}:匹配次数，var regExp=/^[abc]{5}$/
			         {m}:匹配m次
			         {m,n}:匹配m次到n次
			         {m,}:匹配m次或者更多次
			     5）特殊符号：
			       \d：匹配一位数字，相当于[0-9]
			       \D:匹配一位非数字
			       \w:匹配所有字符，包括字母、数字、下划线
			       \W：匹配非字符，除了字母、数字、下划线之外的字符。

			       *：匹配0次或者多次，相当于{0,}
			       +:匹配1次或者更多，相当于{1,}
			       ？：匹配0次或者1次，相当于{0,1}
			 */
			var regExp=/^(([1-9]\d*)|0)$/;
			if (!regExp.test(cost)){
				alert("年龄只能为非负整数");
				return;
			}
			//发送请求
			$.ajax({
				url:'workbench/student/saveCreateStudent.do',
				data:{
					owner:owner,
					name:name,
					age:age,
					email:email
				},
				type:'post',
				dataType:'json',
				success:function(data){
					if (data.code=="1"){
						//关闭模态窗口
						$("#createStudentModal").modal("hide");
						//刷新学生信息，显示第一页数据，保持每页显示条数不变（保留）

					}else{
						//提示信息
						alert(data.message);
						//模态窗口不关闭

					}
				}
			})
		})
		//当学审核信息主页面加载完成，查询所有数据的第一页以及所有数据的总条数；
        queryStudentByConditionForPage();
        //给”查询“按钮添加单击事件
        $("#queryStudentBtn").click(function () {
            //查询所有符合条件的第一页以及所有符合条件数据的总条数；
            queryStudentByConditionForPage();
        })
	});
	function queryStudentByConditionForPage() {
//收集参数
		var name=$("#query-name").val();
        var owner=$("#query-owner").val;
		var age=$("#query-age").val();
        var pageNo=1;
        var pageSize=10;
        //发送请求
        $.ajax({
            url:'workbench/student/queryStudentByConditionForPage.do',
            data:{
                name:name,
            	owner:owner,
				age:age,
                pageNo:pageNo,
                pageSize:pageSize,
            },
            type:'post',
            dataType:'json',
            success:function(data){
                //显示总条数
                $("#totalRowsB").text(data.totalRows);
                //显示学生列表
                //遍历studentList，拼接所有行数据
                var htmlStr="";
                $.each(data.studentList,function(index,obj){
                    htmlStr+="<tr class=\"stu\">";
                    htmlStr+="<td><input type=\"checkbox\" value=\""+obj.id+"\"/></td>";
                    htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.html';\">"+obj.name+"</a></td>";
                    htmlStr+="<td>"+obj.owner+"</td>";
                    htmlStr+="<td>"+obj.age+"</td>";
                    htmlStr+="<td>"+obj.email+"</td>";
                    htmlStr+="</tr>";
                });
                $("#tBody").html(htmlStr);
            }
        })
    }
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createStudentModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">新建学生信息</h4>
				</div>
				<div class="modal-body">
				
					<form id="createStudentForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketStudentOwner" class="col-sm-2 control-label">管理员<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketStudentOwner">
								  <c:forEach items="${userList}" var="u">
									   <option value="${u.id}">${u.teacher}</option>
								  </c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
						<label for="create-marketStudentName" class="col-sm-2 control-label">学生姓名<span style="font-size: 15px; color: red;">*</span></label>
						     <div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-marketStudentName">
							 </div>
						</div>
                        <div class="form-group">
                            <label for="create-age" class="col-sm-2 control-label">年龄</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-age">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 81%;">
								<input type="text" class="form-control" id="create-email">
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveCreateStudentBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改学生信息的模态窗口 -->
	<div class="modal fade" id="editStudentModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改学生信息</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketStudentOwner" class="col-sm-2 control-label">管理员<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketStudentOwner">
									<c:forEach items="${userList}" var="u">
										<option value="${u.id}">${u.teacher}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-control">
							<label for="edit-marketStudentName" class="col-sm-2 control-label">学生姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-marketStudentName" value="小雪">
							</div>
						</div>
						<div class="form-group">
							<label for="edit-age" class="col-sm-2 control-label">年龄</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-age" value="22">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 81%;">
								<input type="text" class="form-control" id="edit-email" value="xiaoxue@qq.com">
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importStudentModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入新生</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="studentFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importStudentBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>学生列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">姓名</div>
							<input class="form-control" type="text" id="query-name">
						</div>
					</div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">管理员</div>
				      <input class="form-control" type="text" id="query-owner">
				    </div>
				  </div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">年龄</div>
							<input class="form-control" type="text" id="query-age">
						</div>
					</div>

				  
				  <button type="button" class="btn btn-default" id="queryStudentBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createStudentBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editStudentModal"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal" ><span class="glyphicon glyphicon-import"></span> 上传列表（导入）</button>
                    <button id="exportStudentAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表（批量导出）</button>
                    <button id="exportStudentXzBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表（选择导出）</button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>姓名</td>
                            <td>管理员</td>
							<td>年龄</td>
							<td>邮箱</td>
						</tr>
					</thead>
					<tbody id="tBody">
					<%--
						<tr class="stu">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">小雪</a></td>
                            <td>zf</td>
							<td>22</td>
							<td>xiaoxue@qq.com</td>
						</tr>
						<tr class="stu">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">小雪</a></td>
							<td>zf</td>
							<td>22</td>
							<td>xiaoxue@qq.com</td>
						</tr>
						--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="totalRowsB">50</b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>