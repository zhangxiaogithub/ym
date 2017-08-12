<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>推荐数据</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/recommendationData/list");
		$("#searchForm").submit();
		return false;
	}
	function queren(id) {
		$.post("${ctx}/recommendationData/update",{id:id},function(result){
			if(result.code == "200"){
				$("#queren_"+id).html("已确认");
			}else{
				alert(result.message);
			}
		});
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
	<form:form id="searchForm" modelAttribute="recommendationData" action="${ctx}/recommendationData/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recommendationData/list">推荐数据</a></li>
	</ul>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>羊毛链接</th>
				<th>推荐理由</th>
				<th>联系方式</th>
				<th>提交时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="entity">
				<tr>
					<td>${entity.id}</td>
					<td>${entity.url}</td>
					<td>${entity.reason}</td>
					<td>${entity.contactInfo}</td>
					<td><fmt:formatDate value="${entity.createDate}" type="date"/></td>
					<c:if test="${entity.status == '1'}">
						<td id="queren_${entity.id}">已确认</td>
					</c:if>
					<c:if test="${entity.status == '0'}">
						<td id="queren_${entity.id}"><font color="red">新提交</font></td>
					</c:if>
					<td><a id="queren" onclick="return queren('${entity.id}')">确认</a> <a
						href="${ctx}/recommendationData/delete?id=${entity.id}"
						onclick="return confirmx('确认要删除该类目吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>