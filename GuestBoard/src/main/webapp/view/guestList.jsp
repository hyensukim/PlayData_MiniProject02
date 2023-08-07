<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container w-80 mt-5 mx-auto">
		<h2>
			<i class="xi-bars"></i> 방명록
		</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">
						<p>제목</p>
					</th>
					<th scope="col">
						<p>작성자</p>
					</th>
					<th scope="col">
						<p>작성일</p>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="g" items="${guestList}" varStatus="status">
					<tr>
						<th scope="row">[${status.count}]</th>
						<td>
						<a href="guest?action=list&gId=${g.gId}" class="text-black text-decoration-none">
								${g.title}</a> 
						</td>
						<td>
							${g.name} 
						</td>
						<td>
							${g.date} 
						</td>
						<td>
						<a href="guest?action=delete&gId=${g.gId}"> <span
								class="badge bg-secondary"> &times;</span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>

		<c:if test="${err != null}">
			<p class="alert alert-danger">${err }</p>
		</c:if>

		<button class="btn btn-info collapsed" type="button"
			data-bs-toggle="collapse" data-bs-target="#c"
			aria-expanded="false" aria-controls="c"> 방명록 글등록
		</button>
		
		<div class="collapse" id="c">
			<div class="card card-body">
				<p class="text-warning">* 필수 입력 사항</p>
				<form name="addForm" method="post" action="/GuestBoard/guest?action=add">
			    	<dl>
			    		<dt><span class="text-warning">*</span> 작성자</dt>
			    		<dd class="">
			    			<input class="w-75" type="text" name="name" placeholder="작성자 이름을 입력해주세요.">
			    		</dd>
			    		
			    		<dt><span class="text-warning">*</span> 비밀번호</dt>
			    		<dd>
			    			<input class="w-75" type="password" name="pass" placeholder="비밀번호를 입력해주세요.">
			    		</dd>
			    		
			    		<dt><span class="text-warning">*</span> 제목</dt>
			    		<dd>
			    			<input class="w-75" type="text" name="title" placeholder="제목을 입력해주세요.">
			    		</dd>
			    		
			    		<dt>내용</dt>
			    		<dd>
			    			<textarea class="w-75 h-100" name="content" placeholder="내용을 입력해주세요.">
			    			</textarea>
			    		</dd>
			    	</dl>
			    	<button type="submit" class="btn btn-success">등록</button>
		    	</form>
			</div>
		</div>
	</div>
</body>
</html>