<%@page import="mul.cam.a.dto.PdsDto"%>
<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	PdsDto pds = (PdsDto) request.getAttribute("pds");
	%>

	<h1>수정</h1>
	<hr>
	<br>
	<br>
	<div align="center">
		<form action="pdsupdateAf.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="seq" value=<%=pds.getSeq()%>>
			<table border="1">
				<col width="150">
				<col width="500">
				<tr>
					<th>아이디</th>
					<td><%=pds.getId()%></td>
				</tr>

				<tr>
					<th>제목</th>
					<td><input type="text" name="title"
						value="<%=pds.getTitle()%>" size="80"></td>
				</tr>

				<tr>
					<th>파일명</th>
					<td><%=pds.getFilename()%>
					<!-- 파일이 변경되지 않았을 경우 대비 -->
					<input type="hidden" name="filename" value="<%=pds.getFilename()%>">
					<input type="hidden" name="newfilename" value="<%=pds.getNewfilename()%>">
					</td>
				</tr>

				<tr>
					<th>수정(대체)할 파일</th>
					<td><input type="file" name="fileload"></td>
				</tr>

				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="80" name="content"></textarea></td>
				</tr>
			</table>

			<tr>
				<td colspan="2"><button type="submit">수정완료</button></td>
			</tr>
		</form>
	</div>

</body>
</html>