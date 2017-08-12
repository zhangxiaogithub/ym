<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>类目管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/bizCategory/list");
		$("#searchForm").submit();
		return false;
	}
</script>
<style type="text/css">
img:hover {
	width: 120%;
	height: 120%
}
</style>
</head>
<body>
	<form:form id="searchForm" modelAttribute="bizCategory" action="${ctx}/bizCategory/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizCategory/list">类目列表</a></li>
		<li><a href="${ctx}/bizCategory/form">类目添加</a></li>
	</ul>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>图片</th>
				<th>权重</th>
				<th>最后编辑时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="entity">
				<tr>
					<td>${entity.title}</td>
					<td><img src="${entity.image}" height="20" width="30" /></td>
					<td>${entity.sort}</td>
					<td><fmt:formatDate value="${entity.updateDate}" type="both"
							dateStyle="full" /></td>
					<c:if test="${entity.status == '1'}">
						<td>已上架</td>
					</c:if>
					<c:if test="${entity.status == '0'}">
						<td><font color="red">已下架</font></td>
					</c:if>
					<td><a href="${ctx}/bizCategory/form?id=${entity.id}">修改</a> <a
						href="${ctx}/bizCategory/delete?id=${entity.id}"
						onclick="return confirmx('确认要删除该类目吗？', this.href)">删除</a> <a
						href="${ctx}/collectContent/list?bizCategoryId=${entity.id}">收录配置</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>