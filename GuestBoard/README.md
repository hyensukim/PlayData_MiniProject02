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
- URL
	- /guestControl → guestList.jsp
	- guestList.jsp
		- 상단 제목
		- 방명록 목록(8개 이상 시, 스크롤 생기도록 구현)
		- 방명록 쓰기(버튼 클릭 시 아래 확장)
		
- View
	- guestList.jsp : 방명록 목록(방명록 삭제), 방명록 글작성(작성자, 비밀번호 → 삭제 시 사용, 제목, 내용)
			- 삭제 버튼 → 비밀번호 입력창 → 비밀번호 일치여부 확인 → 삭제
		- guestView.jsp : 방명록 작성한 글보기
<br><br>

- Model
	- guest.java
	- guestDAO.java
		- DB 추가
		- DB 삭제
		- DB 조회(개별)
		- DB 목록 조회
<br><br>

- Controller
	- guestController.java
		- service()
		- addGuest() : 방명록 쓰기
		- deleteGuest() : 방명록 삭제
		- goodAndBad() : 좋아요 싫어요
	- 삭제 메서드 기능
		1. 삭제 버튼
		2. 비밀번호 입력창 출력(forward 정말 삭제하시겠습니까? 비밀번호를 입력해주세요.)
		3. 비밀번호 일치여부 확인
		4. 일치 -> 삭제, 불일치 -> 잘못된 비밀번호 입니다.
		5. 목록 페이지로 이동.(redirect)
	
<br><br>

- filter
	- 입력 글자 인코딩