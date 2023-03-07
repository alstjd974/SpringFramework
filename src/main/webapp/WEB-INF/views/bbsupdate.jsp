<%@page import="mul.cam.a.dto.BbsDto"%>
<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDto login = (MemberDto)session.getAttribute("login");
%>
    
<%
BbsDto dto = (BbsDto)request.getAttribute("bbsdto");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h2>글 수정</h2>

<div align="center">

<form action="bbsupdateAf.do" method="post">
<input type="hidden" name="seq" value="<%=dto.getSeq() %>" />

<table border="1">
<col width="200"><col width="500">
<tr>
	<th>아이디</th>
	<td><%=dto.getId() %></td>
</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" size="60" value='<%=dto.getTitle() %>'>
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="submit" value="글수정 완료">
	</td>
</tr>
</table>
</form>
</div>




</body>
</html>



