<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>收录数据管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/collectContentData/list");
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
	<form:form id="searchForm" modelAttribute="collectContentData" action="${ctx}/collectContentData/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/collectContentData/list">收录数据管理列表</a></li>
		<li><a href="${ctx}/collectContentData/form">收录数据管理添加</a></li>
	</ul>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>标题</th>
				<th>优惠时间</th>
				<th>所属收录目录</th>
				<th>阅读数（真实）</th>
				<th>评论</th>
				<th>最后编辑时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="entity">
				<tr>
					<td>${entity.id}</td>
					<td>${entity.title}</td>
					<td><fmt:formatDate value="${entity.startDate}" type="date" />~<fmt:formatDate value="${entity.endDate}" type="date" /></td>
					<td>${entity.collectContentTitle}</td>
					<td>${entity.hits}（${entity.actualHits}）</td>
					<td>${entity.commentCount}</td>
					<td><fmt:formatDate value="${entity.updateDate}" type="both"
							dateStyle="full" /></td>
					<c:if test="${entity.status == '1'}">
						<td>已上架</td>
					</c:if>
					<c:if test="${entity.status == '0'}">
						<td><font color="red">已下架</font></td>
					</c:if>
					<td><a href="${ctx}/collectContentData/form?id=${entity.id}">修改</a> <a
						href="${ctx}/collectContentData/delete?id=${entity.id}"
						onclick="return confirmx('确认要删除该类目吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>