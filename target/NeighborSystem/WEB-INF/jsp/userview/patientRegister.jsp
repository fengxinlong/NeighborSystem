<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>疫情打卡</title>

    <style> .layui-form-label {
        width: 300px !important;
        text-align: center !important;
    }

    .layui-input-block {
        margin-left: 300px !important;
    }
    </style>

    <script>
        //页面加载,绑定单机事件
        $(function () {
            $("#btn").click(function () {
                alert("记得要填表哦");
            })
        })
    </script>

</head>

<body>


<div class="layui-col-md10">
<form class="layui-form" action="patient/addPatient" method="post" id="PatientForm">






    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="patientName" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="patientSex" value="男" title="男">
            <input type="radio" name="patientSex" value="女" title="女" checked>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" name="id" required  lay-verify="required" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="patientPhone" required  lay-verify="required" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">健康状态</label>
        <div class="layui-input-block">
            <select name="patientState" lay-verify="required">
                <option value=""></option>
                <option value="0">无新冠肺炎疑似或确诊</option>
                <option value="1">新冠肺炎疑似</option>
                <option value="2">新冠肺炎确诊</option>

            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否发热/呼吸困难/乏力</label>
        <div class="layui-input-block">
            <input type="radio" name="oneStatus" value="是" title="是">
            <input type="radio" name="oneStatus" value="否" title="否" checked>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">过去14天是否有共同居住者(家属或合租者)从其他城市返回情况</label>
        <div class="layui-input-block">
            <input type="radio" name="twoStatus" value="是" title="是">
            <input type="radio" name="twoStatus" value="否" title="否" checked>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">请输入您的情况</label>
        <div class="layui-input-block">
            <textarea name="patientContent" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="submit" lay-filter="patientSubmit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>

    <jsp:include page="../userview/include/foot.jsp" />


<script type="text/javascript">
    layui.use(['form','layer'], function() {
        var form = layui.form;
        var layer=layui.layer;
        form.on('submit(patientSubmit)',function(){
            $.post("patient/addPatient",$("#PatientForm").serialize(),function(data){
                if(data=="success"){
                    layer.msg("填表成功！",{icon:1,anim:4,time:2000},function(){
                        window.location.href="view/index";
                    });
                }else{
                    layer.msg("填表失败！请重试！",{icon:5,anim:6,time:2000});
                }
            });
        });
    });
</script>

<%--<script type="text/javascript">
    layui.use(['form','layer'], function() {
        var form = layui.form;
        var layer=layui.layer;
        form.on('submit(loginSubmit)',function(){
            $.ajax({
                type:"post",
                url:"user/login",
                data:$("#loginForm").serialize(),
                success:function(data){
                    if(data=="success"){
                        layer.msg("登陆成功！",{icon:1,anim:2,time:2000},function(){
                            window.location.href="view/index";
                        });
                    }else{
                        layer.msg("登陆失败！请检查用户名和密码后重试！",{icon:5,anim:6,time:3000});
                    }
                }
            });
        });
    });
</script>--%>


</body>


</html>
