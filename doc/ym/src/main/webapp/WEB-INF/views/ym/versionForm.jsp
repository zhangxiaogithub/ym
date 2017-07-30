<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/version/list">版本管理列表</a></li>
		<li class="active"><a href="${ctx}/version/form?id=${version.id}">用户${not empty version.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="version" action="${ctx}/version/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">渠道:</label>
			<div class="controls">
				<form:input path="canal" htmlEscape="false" maxlength="30" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统os:</label>
			<div class="controls">
				<form:input path="os" htmlEscape="false" maxlength="20" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本:</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="20" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否强制更新：</label>
			<div class="controls">
				<form:radiobuttons path="updateFlag" items="${fns:getDictList('app_update_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="3" maxlength="640" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下载地址:</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="640" class="required"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>