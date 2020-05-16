<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用的SpringMVC注解</title>
</head>
<body>
<a href="testRequestParam?name=hehe">RequestParam</a>
<br>
<form action="testRequestBody" method="post">
    用户姓名:<input type="text" name="uname"><br>
    用户年龄:<input type="text" name="uage"><br>
    <input type="submit" value="提交">
</form>

<a href="testPathVariable/10">PathVariable</a>

<form action="testModelAttribute" method="post">
    用户姓名:<input type="text" name="uname"><br>
    用户年龄:<input type="text" name="uage"><br>
    <input type="submit" value="提交">
</form>

<a href="testSessionAttributes">SessionAttributes</a>

<%--el 表达式11个域对象     requestMap集合--%>


</body>
</html>
