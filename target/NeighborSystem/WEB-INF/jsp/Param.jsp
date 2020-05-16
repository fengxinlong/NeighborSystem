<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>请求参数绑定</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
</head>
<body>
<%--<a href="p?username=hh">请求参数</a>--%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <title>layui.form小例子</title>
    <link rel="stylesheet" href="layui.css" media="all">
</head>
<body>
    <a href="anno/hello">String</a>


</body>
</html>

</body>
</html>
