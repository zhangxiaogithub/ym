<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>收录配置</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/collectContent/list");
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
	<form:form id="searchForm" modelAttribute="collectContent" action="${ctx}/collectContent/list?bizCategoryId=${collectContent.bizCategoryId}" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="bizCategoryId" name="bizCategoryId" type="hidden" value="${collectContent.bizCategoryId}"/>
	</form:form>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/collectContent/list?bizCategoryId=${collectContent.bizCategoryId}">收录配置列表</a></li>
		<li><a href="${ctx}/collectContent/form?bizCategoryId=${collectContent.bizCategoryId}">收录配置添加</a></li>
	</ul>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属类目</th>
				<th>收录名称</th>
				<th>图片</th>
				<th>最后编辑时间</th>
				<th>被关注数</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="entity">
				<tr>
					<td>${entity.categoryTitle}</td>
					<td>${entity.contentTitle}</td>
					<td><img src="${entity.image}" height="20" width="30" /></td>
					<td><fmt:formatDate value="${entity.updateDate}" type="both"
							dateStyle="full" /></td>
					<td>${entity.hits}</td>
					<c:if test="${entity.status == '1'}">
						<td>已上架</td>
					</c:if>
					<c:if test="${entity.status == '0'}">
						<td><font color="red">已下架</font></td>
					</c:if>
					<td><a href="${ctx}/collectContent/form?id=${entity.id}">修改</a> <a
						href="${ctx}/collectContent/delete?id=${entity.id}"
						onclick="return confirmx('确认要删除该类目吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>