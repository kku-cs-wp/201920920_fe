<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
</head>
<body>
    <h1>게시글 목록</h1>
    <a href="<c:url value='/posts/new' />">게시글 작성</a>
    <table border="1">
        <thead>
            <tr>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>상세보기</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <td>${post.title}</td>
                    <td>${post.author}</td>
                    <td>${post.createdAt}</td>
                    <td><a href="<c:url value='/posts/${post.id}' />">보기</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
