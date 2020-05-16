<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
<html>
<head>

    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/layui.css" rel="stylesheet">
    <script src="resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/js/layui.js" type="text/javascript"></script>
    <title>Json</title>

    <script>
        $(function () {
            $("#test").click(function () {
                //编写json格式,设置属性和值
                $.ajax({
                    //编写json格式,设置属性和值
                    url:"json/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    <!--单引号 可嵌套-->
                    data:'{"uname":"hehe","upassword":"123","ubirthday":"2020-1-4"}',
                    <!--通信数据类型-->
                    dataType:"json",
                    <!--请求方式-->
                    type:"post",
                    <!--服务器端响应json的数据-->
                    success:function (data) {
                        //data服务器端响应的json数据，进行解析
                        alert(data);
                        alert(data.uname);
                        alert(data.uage);
                        alert(data.ubirthday);
                    }
                })
            })
        })
    </script>
</head>
<body>
    <button id="test">点我</button>

    <form enctype="multipart/form-data" method="post" action="json/testFileUpload1">
        传统方式
        <input type="file" name="upload"/><br>
        <input type="submit" value="上传"/>
    </form>

    <form action="json/testFileUpload2" enctype="multipart/form-data" method="post">
        <!---->
        SpringMVC
        <input type="file" name="upload"/>
        <input type="submit" value="上传"/>
    </form>

    <form action="json/testFileUpload3" enctype="multipart/form-data" method="post">
        <!---->
        跨服务器上传
        <input type="file" name="upload"/>
        <input type="submit" value="上传"/>
    </form>


</body>
</html>
