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
				<h2><a href="index.jsp">
					<%= session.getAttribute("username") %>
				</a></h2>
				<img src="<%= session.getAttribute("image") %>" alt="<%=session.getAttribute("username") %>" style="width:90px;height:90px;"><br>
								
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
			
			String me = (String) session.getAttribute("username");
			int uid = -1;;
			try {
				uid = udao.getUserByName(me).getUserId();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ArrayList<quiz.bean.History> hist = null;
			hist = qdao.getUserHistory(uid);
			%>
			
			<section>
				<div id="content">
					<br> 
					<% if(request.getParameter("challenged") != null) { %>
						<p style="color:red" > <i>
				  			<font size="2"> <%= request.getParameter("challenged") %> </font> 
				  		</i> </p>
					<%}%>
					
					<h2> My History </h2>
					<% for (int i=0; i<hist.size(); i++) { %>
						<% String name = qdao.getNameByQuizId(hist.get(i).getQuiz_id());
							int bestScore = qdao.getBestScore(uid, hist.get(i).getQuiz_id());
						%>				
						<p> <%= (i+1) + ". " %> <a href=<%= "startQuiz.jsp?quizid=" + hist.get(i).getQuiz_id()%>> <%= name %> </a></p>
						<i>  <%= "   Score:  " + hist.get(i).getScore() %></i><br>
						<i>  <small><%= "  Best Score:  " + bestScore %></small></i>  
						<input type="Submit" value="Challenge User" name=<%=hist.get(i).getQuiz_id() %> onClick="challengeUser(this)"><br>
					 <br>	
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