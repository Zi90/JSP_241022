<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board Modify Page</h1>
	<img alt="" src="/_fileUpload/_th_${bvo.imageFile }">
	<form action="/brd/update" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bno" value="${bvo.bno }">
	<table>
		<thead>
			<tr>
				<th>no.</th>
				<td>${bvo.bno }</td>
			</tr>
			<tr>
				<th>title</th>
				<td> <input type="text" name="title" value="${bvo.title }"> </td>
			</tr>
			<tr>
				<th>writer</th>
				<td>${bvo.writer }</td>
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
				<td> <textarea rows="10" cols="50" name="content">${bvo.content }</textarea> </td>
			</tr>
			<!-- 파일 변경 -->
			<tr>
				<th>imageFile</th>
				<td>
				<input type="hidden" name="imageFile" value="${bvo.imageFile } }">
				<input type="file" name="newFile" accept="image/jpg, image/gif, image/jpeg, image/png">
				</td>
			</tr>
		</thead>
	</table>
	<!-- 수정버튼을 누르면 내가 수정한 내용을 가지고(form => submit) 컨트롤러로 이동 -->	
	<button type="submit">modify commit</button>
	<!-- 리스트 버튼을 누르면 리스트페이지로 이동 -->
	<a href="/brd/list"><button type="button">list</button></a>
	</form>
</body>
</html>