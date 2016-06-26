<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="QuestionSubmit" method="POST">
		<input type="text" placeholder="Your  picture Question url.." size="50" name="quest" />
		<br> <br/>
		<input type="text" placeholder="Correct answer.." size="50"  name="answ" />
		<br> <br/>
		<button> Submit </button>
	</form>

<br> <br/>
<button name="more">Add more questions</button>
	<button name="done">No more questions</button>
</form>
</body>
</html>