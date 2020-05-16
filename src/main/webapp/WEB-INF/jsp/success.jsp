<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>入门成功了</h3>

    ${user.uname}
    ${user.uage}
    ${user.ubirthday}

     ${errorMsg}

    <%System.out.println("success.jsp执行了");%>

</body>
</html>
