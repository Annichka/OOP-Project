<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="CreateQuestion" method="post">
<h6>Your Question</h6>
					
					<input type="text" name="question" placeholder="Your Question" size="50" />
					<h6>Your Answers</h6>
					
					<%for(int i=0; i <8; i++){%>
					
					<input type="text" name=answer<%out.println(i); %>" placeholder="Your Answer1" size="50" /><br/><br/>
					<%} %>
					
					
					
					<input type="checkbox" name="ordered" />Answers must be in order?<br/>
					<br/><input class="timed" type="checkbox" name="timed" placeholder="Allow" />Timed?<br />
										<br> <br/>
	<button name="more">Add more questions</button>
	<button name="done">No more questions</button>
	</form>
</body>
</html>