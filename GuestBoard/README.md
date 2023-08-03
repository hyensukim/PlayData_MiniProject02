# 프로젝트명
- GuestBoard

# 프로젝트 기간
- 2023.08.03 ~ 

# 참여자
- 김현수, 정호진, 신예성, 권윤환

# 기술스택
- JAVA, H2, MAVEN

# 기능설명
- 방명록
    - 홈페이지에 방문한 사용자들이 방명록을 남기는 기능.

# 설계
- View
	- guestList.jsp : 방명록 목록(방명록 삭제), 방명록 글작성(작성자, 비밀번호 → 삭제 시 사용, 제목, 내용)
	- guestView.jsp : 방명록 작성한 글보기
	
- Model
	- guestPost.java
	- guestDAO.java
		- DB 추가
		- DB 삭제
		- DB 조회(개별)
		- DB 목록 조회
	
- Controller
	- guestController.java
	- service()
	
- filter
	- 입력 글자 인코딩