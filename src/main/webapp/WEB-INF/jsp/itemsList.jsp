<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript">
	function deleteItem(){
		document.form.action="${pageContext.request.contextPath }/item/deleteItem.action";
		document.form.submit();
	}
</script>
</head>
<body> 
当前用户：${username}
<c:if test="${username!=null}">
	<a href="${pageContext.request.contextPath }/logout.action">退出</a>
</c:if>
<form name="form" action="${pageContext.request.contextPath }/item/queryItem.action" method="post">
查询条件：
<select>
	<c:forEach items="${itemType}" var="item">
		<option value="${item.key}">${item.value}</option>
	</c:forEach>
</select>
<table width="100%" border=1>
<tr>
<td>
	<input type="submit" value="查询"/>
	<input type="button" value="批量删除" onclick="deleteItem()"/>
</td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>选择</td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
	<td>rest链接</td>
</tr>
<c:forEach items="${itemsList }" var="item">
<tr>
	<td><input type="checkbox" name="delete_id" value="${item.id}"></td>
	<td>${item.name }</td>
	<td>${item.price }</td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH-mm-ss"/></td>
	<td>${item.detail }</td>
	
	<td><a href="${pageContext.request.contextPath }/item/editItem.action?id=${item.id}">修改</a></td>
	<td><a href="${pageContext.request.contextPath }/item/viewItem/${item.id}">商品查看</a></td>

</tr>
</c:forEach>

</table>
</form>
</body>

</html>