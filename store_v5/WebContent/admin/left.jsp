<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<!-- 引入外部css样式 -->
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	<!--引入外部JS -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	//add()对应参数
	//param1: 当前节点id
	//param2: 父节点id
	//param3: 节点上的文字描述
	//param4: 跳转路径
	//param5: 提示信息
	//param6: 发生变化的frame的name属性值
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','分类管理','${pageContext.request.contextPath}/AdminCategoryServlet?method=findAllcarts','','mainFrame');
		d.add('0104','01','商品管理');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/AdminProductServlet?method=findAllProducts&num=1','','mainFrame');
		d.add('010402','0104','已下架商品管理','${pageContext.request.contextPath}/admin/product/pushDown_list.jsp','','mainFrame');
		d.add('0105','01','订单管理');
		d.add('010501','0105','订单管理','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrders','','mainFrame');
		d.add('010502','0105','未付款的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrders&state=1','','mainFrame');
		d.add('010503','0105','已付款订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrders&state=2','','mainFrame');
		d.add('010504','0105','已发货的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrders&state=3','','mainFrame');
		d.add('010505','0105','已完成的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrders&state=4','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
