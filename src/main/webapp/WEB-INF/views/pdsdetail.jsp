<%@page import="mul.cam.a.dto.PdsDto"%>
<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>


<style type="text/css">
th {
	background-color: #007bff;
	color: white;
}

pre {
	white-space: pre-wrap;
	word-break: break-all;
	overflow: auto;
}
</style>

</head>
<body>

	<%
	MemberDto login = (MemberDto) session.getAttribute("login");

	PdsDto pds = (PdsDto) request.getAttribute("pds");
	%>

	<h1>상세 자료보기</h1>
	<hr>

	<div id="app" class="container">

		<table class="table table-striped table-sm">
			<col style="width: 150px" />
			<col style="width: 500px" />

			<tr>
				<th>작성자</th>
				<td><%=pds.getId()%></td>
			</tr>

			<tr>
				<th>작성일</th>
				<td><%=pds.getRegdate()%></td>
			</tr>

			<tr>
				<th>다운로드수</th>
				<td><%=pds.getDowncount()%></td>
			</tr>

			<tr>
				<th>제목</th>
				<td colspan="2" style="font-size: 22px; font-weight: bold"><%=pds.getTitle()%></td>
			</tr>

			<tr>
				<td colspan="2" style="background-color: white;"><pre
						style="font-size: 20px; font-family: 고딕, arial; background-color: white"><%=pds.getContent()%></pre>
				</td>
			</tr>



			<tr>
				<td colspan="2"><input type="button" value="다운로드"
					onclick="filedown(<%=pds.getSeq()%>, '<%=pds.getNewfilename()%>', '<%=pds.getFilename()%>')">
				</td>
			</tr>

		</table>

		<br>

		<button type="button" class="btn btn-primary"
			onclick="location.href='pdslist.do'">다운로드 목록</button>

		<button type="button" class="btn btn-primary" 
		        onclick="updateBtn(<%=pds.getSeq() %>)">수정</button>


	</div>

	<form name="file_down" action="filedownLoad.do" method="post">
		<input type="hidden" name="newfilename"> <input type="hidden"
			name="filename"> <input type="hidden" name="seq">
	</form>


<script type="text/javascript">
function filedown(seq, newfilename, filename){
	document.file_down.newfilename.value = newfilename;
	document.file_down.filename.value = filename;
	document.file_down.seq.value = seq;
	document.file_down.submit();
}

function updateBtn(seq){
	location.href = "pdsupdate.do?seq=" + seq;
}
</script>



</body>
</html>