<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to JSP World~!!</h1>
	<hr>
	<h5>
		<a href="/mem/join">회원가입</a>
	</h5>

	<div>
		<c:if test="${ses.id eq null }">
			<form action="/mem/login" method="post">
				id : <input type="text" name="id" placeholder="id..."> pwd:
				<input type="password" name="pwd" placeholder="pwd...">
				<button type="submit">login</button>
			</form>
		</c:if>
	</div>

	<div>
		<!-- 로그인 이후 나와야 하는 정보 : ses 객체가 있는지 없는지 확인 -->
		<!-- eq:equals	ne:not equals -->
		<c:if test="${ses.id ne null }">
			${ses.id }님이 login 하셨습니다.<br>
			계정생성일 : ${ses.regdate } / 마지막접속일 :  ${ses.lastlogin }<br>
			<a href=""><button type="button">회원정보수정</button></a>
			<c:if test="${ses.id eq 'admin' }">
				<a href=""><button type="button">회원리스트</button></a>
			</c:if>
			<a href=""><button type="button">내가 쓴글 보기</button></a>			
			<a href="/mem/logout"><button type="button">logout</button></a>

		</c:if>
	</div>
	<!-- jsp의 모든 경로는 controller로 이동하게 해야 함. -->
	<h3>
		<a href="/brd/register">board 글쓰기 페이지로 이동</a>
	</h3>
	<h3>
		<a href="/brd/list">board 리스트 페이지로 이동</a>
	</h3>
	
	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}" />`;
		if(msg_login == -1){
			alert('로그인 정보가 일치하지 않습니다.');
		}
	</script>
</body>
</html>