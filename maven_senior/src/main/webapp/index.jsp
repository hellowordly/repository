<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2019/3/20
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<<a href="items/findAll">测试查询所有</a>

<form action="items/save" method="post">
    姓名:<input type="text" name="name"/><br>
    工资:<input type="text" name="price"/><br>
    pic:<input type="text" name="pic"/><br>
    入职时间:<input type="text" name="createtime"/><br>
    描述信息:<input type="text" name="detail"/><br>
    <input type="submit" value="添加">
</form>
</body>
</html>
