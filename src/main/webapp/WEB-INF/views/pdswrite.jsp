<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
MemberDto login = (MemberDto) session.getAttribute("login");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<!-- 일반자료형 뿐만 아니라 binary 데이터도 전송 -->
		<form action="pdsupload.do" method="post"
			enctype="multipart/form-data">

		<table border="1">
			<tr>
				<th>아이디</th>
				<td><%=login.getId()%> <input type="hidden" name="id"
					value="<%=login.getId()%>"></td>
			</tr>

			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="50"></td>
			</tr>

			<tr>
				<th>파일업로드</th>
				<td><input type="file" name="fileload"></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="50" name="content"></textarea></td>
			</tr>

			<tr>
				<td colspan="2">
					<input type="submit" value="자료올리기"></button>
				</td>
			</tr>

		</table>
		
		</form>

	</div>

</body>
</html>