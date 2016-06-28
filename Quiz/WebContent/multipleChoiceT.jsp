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
		<h4>Your Question</h4>
		<input type="text" name="question" placeholder="Your Question"
			size="100" />

		<h4 class="add-input-text">
			<a>Add an answer and check which is correct</a>
		</h4>
		
		<input type="text" name="answer1" placeholder="Your Answer1"
			size="50" /> <input type="checkbox" name="check1"
			placeholder="corr answer1" /> <input type="text" name="answer2"
			placeholder="Your Answer2" size="50" /> <input type="checkbox"
			name="check2" placeholder="corr answer2" /> <input type="text"
			name="answer3" placeholder="Your Answer3" size="50" /> <input
			type="checkbox" name="check3" placeholder="corr answer3" /> <input
			type="text" name="answer4" placeholder="Your Answer4" size="50" />
		<input type="checkbox" name="check4" placeholder="corr answer4" /> <input
			type="text" name="answer5" placeholder="Your Answer5" size="50" />
		<input type="checkbox" name="check5" placeholder="corr answer5" /> <input
			type="text" name="answer6" placeholder="Your Answer6" size="50" />
		<input type="checkbox" name="check6" placeholder="corr answer6" /> <input
			type="text" name="answer7" placeholder="Your Answer7" size="50" />
		<input type="checkbox" name="check7" placeholder="corr answer7" /> <input
			type="text" name="answer8" placeholder="Your Answer8" size="50" />
		<input type="checkbox" name="check8" placeholder="corr answer7" /> <br>
		<br /> <input class="timed" type="checkbox" name="timed"
			placeholder="Allow" />Timed?

		<button name="more">Add more questions</button>
		<button name="done">No more questions</button>

		<form>
</body>
</html>