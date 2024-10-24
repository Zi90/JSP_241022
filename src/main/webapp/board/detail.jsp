<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board Detail Page</h1>
	<img alt="" src="/_fileUpload/${bvo.imageFile }">
	<table>
		<thead>
			<tr>
				<th>no.</th>
				<td>${bvo.bno }</td>
			</tr>
			<tr>
				<th>title</th>
				<td>${bvo.title }</td>
			</tr>
			<tr>
				<th>writer</th>
				<td>${bvo.writer }</td>
			</tr>
			<tr>
				<th>readCount</th>
				<td>${bvo.readCount }</td>
			</tr>
			<tr>
				<th>regdate</th>
				<td>${bvo.regdate }</td>
			</tr>
			<tr>
				<th>moddate</th>
				<td>${bvo.moddate }</td>
			</tr>
			<tr>
				<th>content</th>
				<td>${bvo.content }</td>
			</tr>
		</thead>
	</table>
	<a href="/brd/modify?bno=${bvo.bno }"><button type="submit">update</button></a>
	<a href="/brd/delete?bno=${bvo.bno }"><button type="submit">delete</button></a>
	<a href="/brd/list"><button type="button">list</button></a>
	
	<hr>
	<!-- comment line -->
	<div>
		<h3>Comment Line</h3>
		<input type="text" id="cmtWriter" placeholder="writer..."><br>
		<input type="text" id="cmtText" placeholder="Add Comment...">
		<button type="button" id="cmtAddBtn">post</button>
	</div>
	<br>
	<hr>
	<!-- comment print line -->
	<div id="commentLine">
		<div>
			<div>cno, bno, writer, regdate</div>
			<div>
				<button>수정</button> <button>삭제</button><br>
				<input type="text" value="content">
			</div>
		</div>
	</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno }" />`;
		console.log(bnoVal);
	</script>
	
	<script type="text/javascript" src="/resources/board_detail.js"></script>
	
	<!-- 댓글 리스트 호출 -->
	<script type="text/javascript">
		printList(bnoVal);
	</script>
</body>
</html>