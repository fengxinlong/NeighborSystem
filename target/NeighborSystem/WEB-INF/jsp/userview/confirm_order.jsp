<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>防疫便民小站</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/layui.css" rel="stylesheet">
<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="resources/js/layui.js" type="text/javascript"></script>
</head>
<body>
	<!--导航栏部分-->
	<jsp:include page="include/header.jsp" />

	<!-- 中间内容 -->
	<div class="container-fluid bigHead">
		<div class="row" style="background-color:white;">
			<div style="width:100%;height:120px;text-align:center;background-color:#E8E9ED;">
				<img src="upload/cartbanner4.jpg" style="width:1226px;height:120px;" />
			</div>
			<div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
			<form id="orderForm" action="order/takeOrder" class="layui-form" method="post">
				<h3>请选择收货地址</h3>
				<table>
						<c:if test="${ not empty addrList }">
							<%-- <c:forEach items="${addrList }" var="a">
								 <div class="layui-form-item">
								    <label class="layui-form-label">
								    	<input type="radio" name="addr" value="${a.addrId }">
								    </label>
								    <div class="layui-input-block">
								      <label style="padding-top:7px;">${a.addrProvince }${a.addrCity }${a.addrArea }${a.addrDetail }&nbsp;${a.addrNickname }收&nbsp;${a.addrPhone }</label>
								    </div>
							 </c:forEach>	 --%>
							 <div class="layui-input-block" style="margin-left:0px;width:500px;">
								 <select name="addr" style="width:400px;">
								 	<option value="">请选择收货地址</option>
								 	<c:forEach items="${addrList }" var="a">
								 		<option value=${a.addrId }>${a.addrProvince }${a.addrCity }${a.addrArea }${a.addrDetail }&nbsp;${a.addrNickname }收&nbsp;${a.addrPhone }</option>
								 	</c:forEach>
								 </select>
							 </div>
						</c:if>
						<c:if test="${empty addrList }">
							<button type="button" onclick="javascript:location.href='view/usercenter/#section3'" class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal">去添加地址</button>
						</c:if>	 
				</table>
				<h3>购买商品列表</h3>
				<table class="layui-table" lay-skin="nob" id="shoppingCarTable">
					<thead>
						<tr>
							<th>商品图</th>
							<th>商品名称</th>
							<th>商品单价</th>
							<th>商品数量</th>
							<th>总价</th>
						</tr>
					</thead>
					<c:forEach items="${cartList }" var="c">
						<tbody>
						<tr>
							<input type="hidden" name="goodslist" value="${c.cartId }">
							<td><img style="height:40px;" src="upload/${c.cartGoods.goodsImg }" /></td>
							<td><a style="text-decoration:none" href="goods/detail?goodsId=${c.cartGoods.goodsId }">${c.cartGoods.goodsName }</a></td>
							<td>${c.cartGoods.goodsPrice }元</td>
							<td>${c.cartNum }</td>
							<td><span name="singleTotal">${c.cartGoods.goodsPrice*c.cartNum }</span>元</td>
						</tr>
						</tbody>
					</c:forEach>
				</table>
				<hr />
				<div style='text-align:right;width:1000px;height:50px;font-size:16px;color:red;'><h3>总价：<span id='totalPrice'>0</span>元</h3></div>
				<div class="row" style="text-align:center;">
					<button style="width:300px;" type="button" class="layui-btn layui-btn-warm layui-btn-lg" onclick="confirmPre()">确认下单</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	<!-- 尾部 -->
<jsp:include page="include/foot.jsp" />

<script type="text/javascript">
	var layer;
	layui.use(['layer','form'], function ()
	        {
	            layer = layui.layer;
	            form =layui.form;
	            table=layui.table;
	        });
	$(function(){
		var arr=$("span[name='singleTotal']");
		var total=0;
		for(var i=0;i<arr.length;i++){
			var price=parseInt($(arr[i]).html());
			total+=price;
		}
		$("#totalPrice").html(total);
	});
	function checkSelect(){
		var arr=$("option:checked").val();
		if(arr==null||arr==""){
			return false;
		}
		return true;
	}
	function confirmPre(){
		var flag=checkSelect();
		if(!flag){
			layer.msg('请选择收货地址', { icon: 5, anim: 6 ,shade: 0.4, time: 1000 });
		}else{
			$("#orderForm").submit();
		}
	}
	</script>
</body>
</html>