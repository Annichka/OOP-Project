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

			<%@ page import="java.util.List" %>
			<%@ page import="quiz.bean.*" %>
			<%@ page import="java.util.ArrayList" %>
			<%@ page import="manager.*" %>
			<%@ page import="quiz.dao.*" %>
			<%@ page import="user.dao.*" %>
			<%@ page import="java.io.IOException" %>
			<%@ page import="java.sql.SQLException" %>
			<%@ page import ="javax.servlet.ServletContext" %>
			<%@ page import ="java.util.*" %>
<header>
<a href="index.jsp">Quiz Web Site</a>
</header>
		<%if (session.getAttribute("authorized") == null || session.getAttribute("logout") != null){ %>
			<nav>
			<a href="login.jsp"  >Login</a> <br>
			<a href="register.jsp" >Register</a>
			</nav>
			<section>
				<p>- He saved Klaus to save me, and then he stole the coffins to get even,</p>
				<p>- Damon, if he did it to protect you, why would he even do that? What does that even mean?</p>
				<p>- What does it mean? It means I'm an idiot cuz I thought for one second that I wouldn't heave to feel guilty anymore...</p>
				<p>- Wait, what are you talking about? Guilty for what?</p>
				<p>- For wanting what I want.</p>
				<p>- Damon...</p>
				<p>- No, I know. Believe me, I get it. Brother's girl and all.</p>
				<p>...</p>
				<p>- No, no, you know what? If I'm going to feel guilty about something, I'm going to feel guilty about this?</p>
				<p>I take her head into my hands and kiss her. I act on those feelings pent up inside me and I show them to her through a passionate yet gentle kiss...</p>
				<p>- Goodnight. - I whisper leaving her on that porch while I walk away, barely able to keep myself from looking back.</p>
			</section>
			
		<% } else { %>
			<script src="myscripts.js"></script>

			<nav>
				<% ServletContext sCont = request.getServletContext(); %>
				<h2><a href="index.jsp">
					<%= sCont.getAttribute("username") %>
				</a></h2>
				<img src="<%= sCont.getAttribute("image") %>" alt="<%= sCont.getAttribute("username") %>" style="width:90px;height:90px;"><br>
				
				<%@ include file="panel.jsp" %>
				
				<form action="Logout" method="get">
				    <button> Logout </button><br>
		 		</form>
				
			</nav>
			<section>
				<div id="content">
					<br>
					<%
					String myname = (String) sCont.getAttribute("username");
					UserManager um = (UserManager) getServletContext().getAttribute("userM");
					QuizDao qd = um.getQuizDao();
					UserDao ud = um.getPersonDao();
					QuestionDao qsd = um.getQuestionDao();
					Integer qid = Integer.parseInt((String) request.getParameter("quizid"));
					String quiz_name = qd.getNameByQuizId(qid);
					Quiz curr =  qd.getQuizById(qid);
					
					int author_id = curr.getAuthorId();
					String description = curr.getDescription();
					ArrayList<Question> qstlist = qsd.getQuestionsByQuizId(qid); 
					String author = ud.getUserById(author_id).getUserName();
					int isMultiPage = curr.getPages();
					%>
					
					<h2> <%="Quiz:   " + quiz_name %></h2>
					<% if(author.equals(myname)) {%>
						<a href= <%= "showQuiz.jsp?quizid=" + qid %>>Edit quiz</a><br>
					<% } %>
					
					<h4> Author: &nbsp; <a href=<%= "profile.jsp?profile=" + author %>> <%= author %> </a></h4>
					
					<p><b>Description:</b> <i> <%= description %></i></p>

					<br>
					<% if (isMultiPage == 0) { %>
						<input type="button" id= <%=qid %> value="Start Quiz" name=<%=qid %> onClick="showQuiz(this)"><br>
					<% } else { %>
						<input type="button" id= <%=qid %> value="Start Quiz" name=<%=qid %> onClick="showQuiz(this)"><br>
					<% } %>
					<br><br><hr>
						
						<%@ page import="java.util.List" %>
						<%@ page import="quiz.bean.*" %>
						<%@ page import="java.util.ArrayList" %>
						<%@ page import="manager.*" %>
						<%@ page import="quiz.dao.*" %>
						<%@ page import="user.dao.*" %>
						<%@ page import="java.io.IOException" %>
						<%@ page import="java.sql.SQLException" %>
						<%@ page import ="javax.servlet.ServletContext" %>
						<%@ page import ="java.util.*" %>
						
						<% UserManager uM = (UserManager) getServletContext().getAttribute("userM");
							QuizDao qDao = uM.getQuizDao();
							UserDao uDao = uM.getPersonDao();
							ArrayList<History> recents = qDao.getRecentTakers(qid);
							ArrayList<History> top = qDao.getTopScores(qid);
						%>
						
						<div class="boxes" >
							Recent test takers:<br>
							<% for (int i=0; i<recents.size(); i++) {%>
								<% if (i > 5 ) break; 
								String uName = uDao.getUserById(recents.get(i).getUser_id()).getUserName();
								%>
								<%=(i+1) +". "  %><a href=<%= "profile.jsp?profile=" +  uName
								%>><%= uName %></a> <i> Score: <%=recents.get(i).getScore() %> </i><br>
							<% } %>
						</div>
						
						<div class="boxes" >
							TOP 5:<br>
							<% for (int i=0; i<top.size(); i++) {%>
								<% if (i > 5 ) break; 
								String uName = uDao.getUserById(top.get(i).getUser_id()).getUserName();
								%>
								<%=(i+1) +". "  %><a href=<%= "profile.jsp?profile=" +  uName
								%>><%= uName %></a> <i> Score: <%=top.get(i).getScore() %> </i>&nbsp;&nbsp;
								 <i> Time: <%=top.get(i).getTime() %> </i><br>
							<% } %>
						</div>
						

				</div>
			</section>
		<% } %>
		
<aside>
	<input type="search" id="mySearch" placeholder="Search for friends..">
	<input type="submit" onclick="searchFunc()"/>
</aside>
		
<footer>
	<a href="index.jsp">Home page</a>
</footer>

</body>
</html>