<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2019/3/20
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px">
<tr>
    <td>编号</td>
    <c:forEach items="${items}" var="items">
        <td>${items.id}</td>
    </c:forEach>
</tr>
<tr>
    <td>姓名</td>
    <c:forEach items="${items}" var="items">
        <td>${items.name}</td>
    </c:forEach>
</tr>
<tr>
    <td>工资</td>
    <c:forEach items="${items}" var="items">
        <td>${items.price}</td>
    </c:forEach>
</tr>
    <tr>
        <td>pic</td>
        <c:forEach items="${items}" var="items">
            <td>${items.pic}</td>
        </c:forEach>
    </tr>
    <tr>
        <td>入职时间</td>
        <c:forEach items="${items}" var="items">
            <td>${items.createtime}</td>
        </c:forEach>
    </tr>
    <tr>
        <td>信息描述</td>
        <c:forEach items="${items}" var="items">
            <td>${items.detail}</td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
