<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create your own quiz!</title>
<h1>Creating your own Quiz..</h1>
<p>Choose the type of your question</p>


<form action="BuildQuiz" method="POST">
		<select name="type">
			<option value="01">Picture-Response Questions</option>
			<option value="02">Multiple Choice with Multiple Answers</option>
			<option value="03">Question-Response</option>
			<option value="04">Fill in the blank</option>
			<option value="05">Multiple Choice</option>
			<option value="06">Multi-Answer Questions</option>
			<option value="07">Matching</option>
		</select>
	
	<input type="submit" value="Submit">
</form>

</body>
</html>