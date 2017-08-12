<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收录配置</title>
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
		<li><a href="${ctx}/collectContent/list?bizCategoryId=${collectContent.bizCategoryId}">收录配置</a></li>
		<li class="active"><a href="${ctx}/collectContent/form?id=${collectContent.id}&bizCategoryId=${collectContent.bizCategoryId}">收录配置${not empty collectContent.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="collectContent" action="${ctx}/collectContent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">所属:</label>
			<div class="controls">
				<form:select path="bizCategoryId" class="input-medium required">
					<c:forEach items="${fns:getBizCategoryType('')}" var="bizCategory">
						<c:if test="${bizCategory.id == collectContent.bizCategoryId}">
							<option value="${bizCategory.id}" selected="selected">${bizCategory.title}</option>
						</c:if>
						<c:if test="${bizCategory.id != collectContent.bizCategoryId}">
							<option value="${bizCategory.id}">${bizCategory.title}</option>
						</c:if>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收录名称:</label>
			<div class="controls">
				<form:input path="contentTitle" htmlEscape="false" maxlength="40" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<input type="text" id="image" name="image" value="${collectContent.image}" class="required" />
				<sys:ckfinder input="image" type="thumb" uploadPath="/cms/ym" selectMultiple="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('biz_category_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>