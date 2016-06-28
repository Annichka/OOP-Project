
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="mystyles.css" />
	<script type="text/javascript">
		function newPopup(url) {
			popupWindow = window.open(
				url,'popUpWindow','height=30px, width=50px,left=250,top=150,resizable=no,status=yes')
		}
	</script>
	
	<style>
	input[type=button] {
	     background:none!important;
	     border:none; 
	     padding:0!important;
	     font-family:arial,sans-serif;
	     font-size: 15px;
	     color:green;
	     display:inline-block
	     text-decoration:underline;
	     cursor:pointer;
	
	}
</style>
</head>
<body>
	<%@ page import="manager.UserManager"%>	
	<%@ page import="quiz.dao.QuestionDao"%>
	<%@ page import="quiz.bean.Question"%>
	<%@ page import="java.util.ArrayList"%>
	
	<% ArrayList<Question> qList = (ArrayList<Question>)session.getAttribute("questionlist");  %>
			
	<%for (int i=0; i<qList.size(); i++) { %>
		<% 	Question curr = qList.get(i); 
			String qType = curr.getType(); 
			if(qType == "QR") { 
				String qst = curr.getQuestion();
				String answ = curr.getCAnswer();
				
				%>
				<form action="EditQuestion" method="post">
					<i> <%= (i+1) + ". " + qst %></i>
					<p> <%="Answer: " + answ %></p>
					
					<input type="hidden" name="type" value="QR">
					<button name=<%= curr.getQuestionId() %>>Edit</button>	
				</form>
					
					<form action="FinishQuiz" method="post">
						<button name="done" >No more questions</button>
					</form>

		<% }%>	
	<%} %>
</body>
</html>
