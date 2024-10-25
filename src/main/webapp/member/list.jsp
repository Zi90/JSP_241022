<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>email</th>
				<th>phone</th>
				<th>regdate</th>
				<th>readCount</th>
			</tr>
		</thead>
		<tobody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/brd/detail?bno=${bvo.bno }">
					<img alt="" src="/_fileUpload/_th_${bvo.imageFile }">
					${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.regdate }</td>
					<td>${bvo.readCount }</td>
				</tr>
			</c:forEach>
		</tobody>
	</table>
</body>
</html> --%>