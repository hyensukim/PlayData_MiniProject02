<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<style>
.comment-container {
	border: 1px solid #ccc;
	padding: 10px;
	margin-bottom: 10px;
}

.comment-header {
	font-size: 16px;
	font-weight: bold;
	margin-bottom: 10px;
}

.comment-content {
	font-size: 14px;
	margin-bottom: 10px;
}

.comment-date {
	font-size: 12px;
	color: #888;
}

/* 방명록 5.5개씩 보기(height로 조절) */
.comment-container2 {
	height: 550px;
	overflow-y: auto;
}

.comment-input {
	margin-bottom: 10px;
}

.comment-delete {
	text-align: right;
	font-size: 14px;
}

.sp-right {
	font-size: 14px;
	float: right;
}
</style>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h1 style="font-family: 나눔고딕;">방명록</h1>
		<!-- 방명록 작성 폼 -->
		<div class="comment-form">
			<!-- <form action="/GuestBoard/GBControl?action=insert" method="post">  -->
			<form action="/GuestBoard/GBControl" method="post">
				<input type="hidden" name="action" value="insert">
				<div class="comment-input">
					<label for="nickname">별명 </label> <input type="text" id="nickname"
						name="nickname" required> <label for="nickname">비밀번호
					</label> <input type="text" id="password" name="password" required>
				</div>
				<div class="comment-input">
					<label for="content">내용 </label>
					<textarea placeholder="댓글을 입력하세요" id="content" name="content"
						rows="4" cols="65" required></textarea>
					<span class="sq-right"><input type="submit" value="등록"></span>
				</div>
			</form>
		</div>

		<hr>

		<h2 style="font-family: 나눔고딕;">방명록 리스트</h2>
		<!-- 방명록 리스트 -->
		<c:set var="guestBoardCount" value="${fn:length(guestBoards)}" />
		<h3 style="font-size: 16px; font-sytle: 나눔고딕;">방명록 개수:
			${guestBoardCount}</h3>
		<div class="comment-container2">
			<c:forEach items="${guestBoards}" var="guestBoard" varStatus="loop">
				<div class="comment-container">
					<div class="comment-header">${guestBoard.nickname}</div>
					<div class="comment-content">${guestBoard.content}</div>
					<div class="comment-date">${guestBoard.date}
						<span class="sp-right"> 
						<!-- <a href="/GuestBoard/GBControl?action=delete&aid=${guestBoard.aid})">삭제</a> -->
						<a href="#" onclick="openGBDelete(${guestBoard.aid}, '${guestBoard.password}')">삭제</a>
						</span>
					</div>
				</div>
				<c:if test="${loop.index == 4}">
					<c:set var="lastIndex" value="${loop.index}" />
				</c:if>
			</c:forEach>
		</div>
		<!-- 5개 보다 많으면 더보기 -->
		<c:if test="${guestBoardCount > 5}">
			<p>
				<a href="#" onclick="showMoreGB()">더보기</a>
			</p>
		</c:if>
		<script>
			// 더보기
			function showMoreGB() {
				var container = document
						.querySelector(".comment-container-container");
				container.style.height = (container.offsetHeight + 300) + "px";
			}
			
			// 삭제
			function openGBDelete(aid, password) {
		        var inputPassword = prompt("비밀번호를 입력하세요:");
		        
		        if (inputPassword !== null) {
		            if (inputPassword === password) {
		                if (confirm("정말로 삭제하시겠습니까?")) {
		                    // DAO.delete() 호출
		                    window.location.href = "/GuestBoard/GBControl?action=delete&aid=" + aid;
		                }
		            } else {
		                alert("비밀번호가 일치하지 않습니다.");
		            }
		        }
		    }
		</script>
	</div>
</body>
</html>