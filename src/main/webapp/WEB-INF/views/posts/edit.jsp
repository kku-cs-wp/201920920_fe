<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
</head>
<body>
    <h1>게시글 수정</h1>
    
    <!-- c:url 태그를 사용하여 URL을 변수에 할당 -->
    <c:url var="updatePostUrl" value="/posts/${post.id}" />
    
    <!-- form:form 태그의 action 속성에 변수 사용 -->
    <form:form method="post" modelAttribute="post" action="${updatePostUrl}">
        <p>
            <form:label path="title">제목:</form:label>
            <form:input path="title" />
        </p>
        <p>
            <form:label path="content">내용:</form:label>
            <form:textarea path="content" rows="10" cols="50" />
        </p>
        <p>
            <form:label path="author">작성자:</form:label>
            <form:input path="author" />
        </p>
        <p>
            <input type="submit" value="수정" />
        </p>
    </form:form>
    
    <a href="<c:url value='/posts/${post.id}' />">상세보기</a>
</body>
</html>
