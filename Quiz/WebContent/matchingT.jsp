<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1> Create the matching pairs</h1>
<form action="CreateQuestion" method="post">
	<h6 class="add-match-text">
		
	</h6>
	<% for(int i=0; i < 8; i++){ %>
		<br />
		<br />Elem
		<input type="text" name="question<%=i%>" placeholder="Your Question"
			size="25" /> Matching Elem
		<input type="text" name="answer<%=i%>" placeholder="Your Answer" size="25" />
		
	<% }%>
	<br/>
	<br/>
	<br />
	<input class="timed" type="checkbox" name="timed" placeholder="Allow" />Timed?
	<br />
	<br> <br/>
	<button name="more">Add more questions</button>
	<button name="done">No more questions</button>
</form>
</body>
</html>