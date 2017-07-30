\<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片管理</title>
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
		<li class="active"><a href="${ctx}/icon/form?id=${image.id}">图片管理</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="image" action="${ctx}/image/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">头部图（选填）：</label>
			<div class="controls">
				width：<input type="text" id="width1" name="width1" value="${image.width1}" class="required digits" />
				height：<input type="text" id="height1" name="height1" value="${image.height1}" class="required digits" />
			</div>
			<div class="controls">
				<input type="text" id="image1" name="image1" value="${image.image1}" class="required" />
				<sys:ckfinder input="image1" type="thumb" uploadPath="/cms/ym" selectMultiple="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">尾部图（选填）：</label>
			<div class="controls">
				width：<input type="text" id="width2" name="width2" value="${image.width2}" class="required digits" />
				height：<input type="text" id="height2" name="height2" value="${image.height2}" class="required digits" />
			</div>
			<div class="controls">
				<input type="text" id="image2" name="image2" value="${image.image2}" class="required" />
				<sys:ckfinder input="image2" type="thumb" uploadPath="/cms/ym" selectMultiple="false"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>