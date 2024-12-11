<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${post.title}</title>
</head>
<body>
    <h1>${post.title}</h1>
    <p><strong>작성자:</strong> ${post.author}</p>
    <p><strong>작성일:</strong> ${post.createdAt}</p>
    <p><strong>내용:</strong></p>
    <p>${post.content}</p>
    <a href="<c:url value='/posts/${post.id}/edit' />">수정</a>
    <form action="<c:url value='/posts/${post.id}/delete' />" method="post" style="display:inline;">
        <input type="submit" value="삭제" onclick="return confirm('정말 삭제하시겠습니까?');" />
    </form>
    <br/><br/>
    <a href="<c:url value='/posts' />">목록으로</a>
</body>
</html>
