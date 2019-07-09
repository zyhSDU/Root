<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>所有用户页面</title>
</head>

<body>

<form action="MovieServlet" method="post">
    关键词：<input type="text" name="text" value=""> <input type="submit" value="搜索">

</form>
<table width="600" border="1" cellpadding="0">
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>豆瓣评分</th>
        <th>评分人数</th>
        <th>主演</th>
        <th>上映时间</th>
        <th>url</th>
        <th>url2</th>
        <th>url3</th>

    </tr>
    <c:forEach var="U" items="${userAll}">
        <form action="MovieServlet" method="post">
            <tr>
                <td><input size="5" type="text" value="${U.id}" name="id"></td>
                <td><input size="20" type="text" value="${U.name}" name="name"></td>
                <td><input size="5" type="text" value="${U.douban_pingfeng}" name="douban_pingfeng"></td>
                <td><input size="5" type="text" value="${U.douban_pingfeng_renshu}" name="douban_pingfeng_renshu"></td>
                <td><input size="30" type="text" value="${U.star}" name="star"></td>
                <td><input size="30" type="text" value="${U.releaseTime}" name="releaseTime"></td>
                <td><input size="50" type="text" value="${U.url}" name="url" onclick="window.location.href='${U.url}'"></td>
                <td><input size="30" type="text" value="${U.url2}" name="url2" onclick="window.location.href='${U.url2}'"></td>
                <td><input size="30" type="text" value="" name="url3" ></td>

            <%--                <td><a href="DeleteServlet?id=${U.id}">删除</a>  <input type="submit" value="更新"/></td>--%>
            </tr>
        </form>
    </c:forEach>
</table>
<p class="paging">
    <a href="MovieServlet?page=${paging.indexpage-1}">&lt;&lt; 首页 </a>
    <a href="MovieServlet?page=${paging.page-1 }"> &lt; 上一页 </a>
    <strong>第${paging.page+1}页/共${paging.pagenumber}页</strong>
    <a href="MovieServlet?page=${paging.page+1}">下一页 &gt;</a>
    <a href="MovieServlet?page=${paging.pagenumber-1}">末页 &gt;&gt;</a>
</p>
<br/>
<br/>
</body>
</html>
