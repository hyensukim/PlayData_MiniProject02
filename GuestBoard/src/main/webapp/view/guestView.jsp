<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성글</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>${guest.title }</h2>
		<hr>
		<h3>작성일 : ${guest.date }</h3>
		<div class="card w-75 wx-auto">
			<div class="card-body">
				<p class="card-text">
				내용 : ${guest.content }
				</p>
			</div>
		</div>
	</div>
</body>
</html>