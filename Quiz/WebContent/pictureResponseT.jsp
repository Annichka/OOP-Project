<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>picture response question</title>
</head>
<body>
	<form action="CreateQuestion" method="post">
	<h6>Copy picture URL</h6>
	<input type="text" name="question" placeholder="URL" size="50" />
	<br />
	<h6>Your Answer</h6>
	<br />
	<input type="text" name="answer" placeholder="Your Answer" size="50" />
	<br />
	<br />

	<br />
	<input class="timed" type="checkbox" name="timed" placeholder="Allow" />Timed?
	<br />
	<br>
	<br />
	<button name="more">Add more questions</button>
	<button name="done">No more questions</button>
	</form>
</body>
</html>