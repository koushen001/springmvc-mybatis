<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json测试</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	function requestJson(){
		$.ajax({
			url:"${pageContext.request.contextPath}/requestJson.action",
			type:"post",
			contentType:"application/json;charset=utf-8",
			//请求json数据，使用json表示商品信息
			data:'{"name":"手机","price":1999}',
			success:function(data){
				alert(data.name);
			}
		});
	}
	function responseJson(){
		$.ajax({
			url:"${pageContext.request.contextPath}/responseJson.action",
			type:"post",
			data:"name=手机&price=1999",
			success:function(data){
				alert(data.name);
			}
		});
	}
</script>
</head>
<body>
<input type="button" value="请求json响应json" onclick="requestJson()">
<input type="button" value="请求key/value响应json" onclick="responseJson()">
</body>
</html>