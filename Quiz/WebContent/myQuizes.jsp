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
	<%@ page import="user.dao.*" %>
	<%@ page import="user.bean.*" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="manager.*" %>
	<%@ page import="quiz.dao.*" %>
	<%@ page import="quiz.bean.*" %>
	<%@ page import="java.io.IOException" %>
	<%@ page import="java.sql.SQLException" %>
	<%@ page import ="javax.servlet.ServletContext" %>
	

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
				<img src="<%= sCont.getAttribute("image") %>" alt="<%=sCont.getAttribute("username") %>" style="width:90px;height:90px;"><br>
								
				<%@ include file="panel.jsp" %>
				
				<div class="form">
				  <form action="Logout" method="get">
				    <button> Logout </button><br>
				  </form>
				</div>
			</nav>
			
			<%
			UserManager man = (UserManager) getServletContext().getAttribute("userM");
			QuizDao qdao = man.getQuizDao();
			UserDao udao = man.getPersonDao();
			User me = udao.getUserByName((String)sCont.getAttribute("username"));
			
			int uid = me.getUserId();
			
			ArrayList<Quiz> myquizes = qdao.getQuizByCreator(uid);
			ArrayList<Quiz> unfinished = qdao.getUnfinishedQuizByCreator(uid);
			%>
			
			<section>
				<div id="content">
					<br> 
					<% if(request.getParameter("challenged") != null) { %>
						<p style="color:red" > <i>
				  			<font size="2"> <%= request.getParameter("challenged") %> </font> 
				  		</i> </p>
					<%}%>
					
					<h2> My Quizes </h2>
					<br>
					<h4> Finished: </h4>
					<% for (int i = 0; i < myquizes.size(); i++) { %>
						<% String name = qdao.getNameByQuizId(myquizes.get(i).getQuizId());
						%>				
						<p> <%= (i+1) + ". " %> <a href=<%= "startQuiz.jsp?quizid=" + myquizes.get(i).getQuizId()%>> <%= name %> </a>
						&nbsp;&nbsp;
						<a type="likeabutton" href= <%= "showQuiz.jsp?quizid=" + myquizes.get(i).getQuizId() %>>Edit quiz</a>
						</p>
					 <% } %>
					 <hr>
					 <h4> In process: </h4>
					<% for (int i = 0; i < unfinished.size(); i++) { %>
						<% String name = qdao.getNameByQuizId(unfinished.get(i).getQuizId());
						%>				
						<p> <%= (i+1) + ". " %> <a href=<%= "startQuiz.jsp?quizid=" + unfinished.get(i).getQuizId()%>> <%= name %> </a>
						&nbsp;&nbsp;
						<a href= <%= "showQuiz.jsp?quizid=" + unfinished.get(i).getQuizId() %>>Edit quiz</a>
						</p>
					 <% } %>
					 
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