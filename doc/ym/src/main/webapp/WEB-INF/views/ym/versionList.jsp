<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>版本管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/version/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/version/list">版本列表</a></li>
		<li><a href="${ctx}/version/form">版本添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="version" action="${ctx}/version/list" method="post" class="breadcrumb form-search ">
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>渠道</th>
				<th>系统os</th>
				<th>版本</th>
				<th>强制更新</th>
				<th>描述</th>
				<th>地址</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="v">
				<tr>
					<td>${v.canal}</td>
					<td>${v.os}</td>
					<td>${v.version}</td>
					<c:if test="${v.updateFlag == '1'}">
						<td>强制更新</td>
					</c:if>
					<c:if test="${v.updateFlag == '0'}">
						<td>不强制更新</td>
					</c:if>
					<td>${v.description}</td>
					<td>${v.url}</td>
					<td><a href="${ctx}/version/form?id=${v.id}">修改</a> <a
							href="${ctx}/version/delete?id=${v.id}"
							onclick="return confirmx('确认要删除该记录吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>