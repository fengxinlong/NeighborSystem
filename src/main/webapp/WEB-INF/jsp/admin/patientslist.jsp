<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../../../resources/css/layui.css">
<link rel="stylesheet" href="../../../resources/css/style.css">

	<!--引入文件-->
<script type="text/javascript" src="../../../resources/js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="../../../resources/js/layui.js"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<div class="layui-form-item" style="margin:15px;height:30px;">
    <div class="layui-input-inline">
        <input type="text" id="filter" name="filter" value="" lay-verify="" placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-input-inline">
        <button class="layui-btn" id="search" data-type="reload" name="search">
            <i class="layui-icon"></i>搜索
        </button>
    </div>
    <div style="float:right;width:110px;height:35px;">
	    <div class="layui-input-inline" style="width:100px;">
	     	<button class="layui-btn layui-btn-normal" id="delete" name="delete">
	            <i class="layui-icon">&#x1006;</i>批量删除
	        </button>
	    </div>
    </div>
</div>
<div>
    <table id="patient" lay-filter="patient"></table>
</div>
<div id="editForm" style="display:none;width:800px;padding-top:10px;">
    <form id="formData" class="layui-form">
    	<input type="hidden" name="patientId" id="patientId" >
		<div class="layui-form-item">
			<label class="layui-form-label">健康状态</label>
			<div class="layui-input-block">
				<select name="patientStatus" id="patientStatus" lay-verify="required">
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
				<textarea id="patientContent" name="patientContent" placeholder="请输入内容" class="layui-textarea"></textarea>
			</div>
		</div>
    </form>
</div>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-normal" title="编辑" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" title="刪除" lay-event="delete">刪除</a>
</script>
<script type="text/html" id="dateTpl">
    {{ layui.laytpl.fn(d.editdate) }}
</script>
<script type="text/javascript">
var table;
var layer;
var form;

        layui.use(['layer', 'table','form'], function ()
        {
            table = layui.table;
            layer = layui.layer;
            form =layui.form;

            layui.laytpl.fn = function (value)
            {
                //json日期格式转换为正常格式
                var date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                return date.getFullYear() + "-" + month + "-" + day;
            };



            
            //--------------方法渲染TABLE----------------
            var tableIns = table.render({
                elem: '#patient'
                , id: 'patient'
                , url: 'patient/findPatientBySplitPage'
                , cols: [[
                     { checkbox: true, LAY_CHECKED: false } //其它参数在此省略
                      , { field: 'patientId', title: '编号', width: 80, align: 'center' }
                     , { field: 'patientName', title: '病人名字', width: 140, align: 'center' }
                    , { field: 'patientSex', title: '性别', width: 80,sort:true, align: 'center' }
                    , { field: 'patientPhone', title: '电话', width: 160,sort:true, align: 'center' }
                    , { field: 'patientState', title: '健康状态', width: 80, align: 'center' }
                    , { field: 'oneStatus', title: '有无发热，头晕', width: 160, align: 'center' }
                    , { field: 'twoStatus', title: '有无接触', width: 160, align: 'center' }
                    , { field: 'patientContent', title: '具体症状', width: 160, align: 'center' }
                    , {title: '操作', fixed: 'right', width: 120, align: 'center', toolbar: '#bar' }
                ]]
                , page: true
                , limits: [5, 10, 15]
                , limit: 10 //默认采用10
                , done: function (res, curr, count)
                {
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    //console.log(res);
                    //得到当前页码
                    //console.log(curr);
                    $("#curPageIndex").val(curr);
                    //得到数据总量
                    //console.log(count);
                }
            });

            //#region --------------搜索----------------
            $("#search").click(function ()
            {
                var strFilter = $("#filter").val();
                tableIns.reload({
                    where: {
                        keyword: strFilter
                    },page: {
                    curr: 1 //重新从第 1 页开始
                  }
                });
            });
            //#endregion

            //#region --------------批量删除----------------
            $("#delete").click(function ()
            {
                var checkStatus = table.checkStatus('goods');
                var count = checkStatus.data.length;//选中的行数
                if (count > 0)
                {
                    parent.layer.confirm('确定要删除所选商品？', { icon: 3 }, function (index)
                    {
                        var data = checkStatus.data; //获取选中行的数据
                        var batchId = '';
                        for (var i = 0; i < data.length; i++)
                        {
                        	batchId += data[i].goodsId + ",";
                        }
                        $.ajax({
                            url: 'goods/batchDelete',
                            type: "post",
                            data: { 'batchId': batchId },
                            success: function (result){
                                if (result=="success"){
                                     parent.layer.msg('删除成功', { icon: 1, shade: 0.4, time: 1000 })
                                     $("#search").click();//重载TABLE
                                }else{
                                     parent.layer.msg("删除失败", { icon: 5, shade: [0.4], time: 1000 });
                                }
                                parent.layer.close(index);
                            }
                        })
                    });
                }
                else
                    parent.layer.msg("请至少选择一条数据", { icon: 5, shade: 0.4, time: 1000 });
            });
            //#endregion

            //#endregion
            //工具条事件监听
            table.on('tool(patients)', function (obj)
            { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                var patientId = data.patientId;
                if (layEvent === 'edit')
                { //编辑
                    layer.open({
                        type: 1,
                        title: '编辑病人信息',
                        shade: 0.4,  //阴影度
                        fix: false,
                        shadeClose: true,
                        maxmin: false,
                        area: ['900px;', '540px;'],    //窗体大小（宽,高）
                        content: $('#editForm'),
                        success: function (layero, index)
                        {
                            var body = layer.getChildFrame('body', index); //得到子页面层的BODY
                            $("#patientId").val(data.patientId);
                            $("#patientStatus").val(data.patientStatus);
                           	$("#oneStatus").val(data.oneStatus);
                           	$("#twoStatus").val(data.twoStatus);
                           	$("#patientContent").val(data.patientContent);
                            form.render();
                            body.find('#hidValue').val(index); //将本层的窗口索引传给子页面层的hidValue中
                        },
                        btn:['修改','取消'],
                        yes: function(index, layero){
                        	$.post('goods/updateGoods',$('#formData').serialize(),function(data){
                                if (data == 'success')
                                {
                                    parent.layer.msg('修改成功', { icon: 1, shade: 0.4, time: 1000 });
                                    $("#search").click();
                                    $("#handle_status").val('');
                                }
                                else
                                {
                                    parent.layer.msg('修改失败', { icon: 5, shade: 0.4, time: 1000 });
                                }
                                $("#newImg").html("");
                                layer.close(index);
                        	}); 
                        }
                    });
                }else if(layEvent === 'delete'){
                	layer.confirm('是否删除该商品？', {
                		  btn: ['确认', '取消'] //可以无限个按钮
                		  ,btn1: function(index, layero){
                			  $.ajax({
                				   type: "POST",
                				   url: "goods/delete",
                				   data: "goodsId="+data.goodsId,
                				   success: function(msg){
                				     if(msg=='success'){
                				    	 parent.layer.msg('删除成功', { icon: 1, shade: 0.4, time: 1000 });
                				     }else{
                				    	 parent.layer.msg('删除失败', { icon: 5, shade: 0.4, time: 1000 });
                				     }
                				   }
                				});
                			  $(tr).remove();
                			  layer.close(index);
                		  }
                		});
                }
            });
        });
    </script>
</body>
</html>