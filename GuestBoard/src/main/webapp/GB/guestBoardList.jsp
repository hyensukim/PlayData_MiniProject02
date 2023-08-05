<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>방명록</title>
    <style>
        /* 스타일 수정은 필요에 따라 하세요. */
        .comment-container {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }

        .comment-header {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .comment-content {
            font-size: 14px;
            margin-bottom: 10px;
        }

        .comment-date {
            font-size: 12px;
            color: #888;
        }
    </style>
</head>
<body>
    <h1>방명록</h1>
    <!-- 방명록 작성 폼 -->
    <div class="comment-form">
        <form action="/GBControl" method="post">
            <input type="hidden" name="action" value="insert">
            <div class="comment-input">
                <label for="nickname">별명 </label>
                <input type="text" id="nickname" name="nickname" required>
            </div>
            <div class="comment-input">
                <label for="content">내용 </label>
                <textarea placeholder="댓글을 입력하세요" id="content" name="content" rows="4" cols="50" required></textarea>
            </div>
            <input type="submit" value="등록">
        </form>
    </div>
    
    <hr>
    
    <h2>방명록 리스트</h2>
    <!-- 방명록 리스트 -->
    <c:forEach items="${guestBoards}" var="guestBoard">
        <div class="comment-container">
            <div class="comment-header">${guestBoard.nickname}</div>
            <div class="comment-content">${guestBoard.content}</div>
            <div class="comment-date">${guestBoard.date}</div>
        </div>
    </c:forEach>
</body>
</html>