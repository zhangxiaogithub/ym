\<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>启动页管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
		<li class="active"><a href="${ctx}/startPage/form?id=${startPage.id}">启动页</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="startPage" action="${ctx}/startPage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">选择图片（必填）：</label>
			<div class="controls">
			<input type="text" id="image" name="image" value="${startPage.image}" class="required" />
				<sys:ckfinder input="image" type="thumb" uploadPath="/cms/ym" selectMultiple="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示时长（必填）：</label>
			<div class="controls">
				<form:input path="time" htmlEscape="false" maxlength="5" class="required digits input-small"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可跳过（必填）：</label>
			<div class="controls">
				<form:radiobuttons path="jump" items="${fns:getDictList('jump')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接地址（选填）：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" rows="3" maxlength="800" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>