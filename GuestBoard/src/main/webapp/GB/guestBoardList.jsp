<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<title>GUESTBOARD</title>
</head>
<body>
	<div class = "container w-75 mt-5 mx-auto">
	<h2><b>방명록</b></h2>
	<hr>
	<a href = "/GuestBoard/GBControl">새로고침</a>
		<!-- 
 		<ul class="list-group">
			<c:forEach var="guestboard" items="${gusetBoardList}" varStatus="status">
				<li class="list-group-item list-group-item-action d-flex justify-content-between align-item-center">
					<a href="GBControl?action=list&aid=${guestboard.aid}" class="text-decoration-none">[${status.count}] ${guestboard.content},${guestboard.date}</a> 
					<a href="GBControl?action=delete&aid=${guestboard.aid}"> <span class="badge bg-secondary">&times;</span></a>
				</li>
			</c:forEach>
		</ul> 
		<button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#addForm"
			aria-expanded="false" aria-controls="addForm">댓글 등록</button>
			<div class="collapse" id="addForm">
			<div class="card card-body">
				<form method="post" action="/GuestBoard/GBControl?action=insert" enctype="multipart/form-data">
					<label class="form-label">별명</label> 
					<input type="text" name="nickname" class="form-control"> 
					<label class="form-label">내용</label> 
					<textarea rows="10" cols="5" name="content" class="form-control"></textarea>  
					<button type="submit" class="btn btn-success mt-3">저장</button>
				</form>
			</div>
		</div>
 -->	
		<table border = "1">
		<tr><th>no</th></tr>
		<tr><th>별명</th></tr>
		<tr><th>내용</th></tr>
		<tr><th>등록시간</th></tr>
		<c:forEach items="${guestBoards}" var = "GBs">
			<tr>
				<td>${GBs.aid}</td> <td>${GBs.nickname}</td><td>${GBs.content}</td><td>${GBs.date}</td>
			</tr>
		</c:forEach>
		</table>
		<hr>
		<h2><b>댓글</b></h2>
		<hr>
		<form method="post" action="/GuestBoard/GBControl?action=insert">
		<label>닉네임</label>
		<input type="text" name="nickname"><br>
		<label>댓글</label>
		<input type="text" name="content"><br>
		<button type="submit">등록</button>
		</form>
		<!-- 
		<button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#addForm"
			aria-expanded="false" aria-controls="addForm">댓글 등록</button>
			<div class="collapse" id="addForm">
			<div class="card card-body">
				<form method="post" action="/GuestBoard/GBControl?action=insert" enctype="multipart/form-data">
					<label class="form-label">별명</label> 
					<input type="text" name="nickname" class="form-control"> 
					<label class="form-label">내용</label> 
					<textarea rows="10" cols="5" name="content" class="form-control"></textarea>  
					<button type="submit" class="btn btn-success mt-3">저장</button>
				</form>
			</div>
		</div>
		 -->
	</div>
</body>
</html>