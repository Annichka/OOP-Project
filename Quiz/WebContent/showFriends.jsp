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

			<%
			UserManager man = (UserManager) getServletContext().getAttribute("userM");
			QuizDao qdao = man.getQuizDao();
			UserDao udao = man.getPersonDao();
			String username = (String) request.getParameter("profile"); 
			User usr = null;
			Integer logged_user_id = (Integer) request.getServletContext().getAttribute("id");
			FriendsDao fDao = ((FriendManager)getServletContext().getAttribute("friM")).getFriendDao();
	
			try {
				usr = udao.getUserByName(username);				
			 } catch (SQLException e) {
				 e.printStackTrace();
			}
			boolean areFriends = fDao.isFriend(logged_user_id, usr.getUserId());
			boolean isRequested = fDao.isRequested(logged_user_id, usr.getUserId());
			boolean reverseRequested = fDao.isRequested(usr.getUserId(), logged_user_id);
			
			ArrayList<Friends> frs = (ArrayList<Friends>) fDao.getFriendList(usr.getUserId());
			
			%>
			
			<% if (areFriends) { %>
			<nav>
				<p style="color:blue;"><%= request.getParameter("profile") %>'s Profile</p>
				<img src="<%= usr.getUserpic() %>" alt="<%= usr.getUserName()%>" style="width:90px;height:90px;"/>
				<div class="form">
					<form action="Unfriend" method="post">
						<input type="hidden" name="unfriendTo" value="<%=usr.getUserName() %>">
					    <input type="submit" value="Unfriend" />
					</form>
				</div>
				
				<a href=<%= "showFriends.jsp?profile=" +  usr.getUserName() %>> Friends </a><br>
				<a href=<%= "showHistory.jsp?profile=" +  usr.getUserName() %>> History </a><br>
			</nav>
		<% } else if (reverseRequested){%>
				<nav>		
				<p style="color:blue;"><%= request.getParameter("profile") %>'s Profile</p>
				
				<img src="<%= usr.getUserpic() %>" alt="<%= usr.getUserName()%>" style="width:90px;height:90px;"/>
	
				<div class="form">
				  <form action="AcceptRequest" method="post">
				  	<input type="hidden" name="acceptfrom" value="<%=usr.getUserName() %>">
				  	<input type="hidden" name="returntouser" value="<%=usr.getUserName() %>">
				    <input type="submit" value="Accept Request" />
				   </form>
				</div>
				
				<a href=<%= "showFriends.jsp?profile=" +  usr.getUserName() %>> Friends </a><br>
				<a href=<%= "showHistory.jsp?profile=" +  usr.getUserName() %>> History </a><br>
				<a href="society.jsp"> Society </a><br>
				
			</nav>
					
		<% } else { %>
			<nav>		
				<p style="color:blue;"><%= request.getParameter("profile") %>'s Profile</p>
				
				<img src="<%= usr.getUserpic() %>" alt="<%= usr.getUserName()%>" style="width:90px;height:90px;"/>
	
				<% if (isRequested) { %>
					<div class="form">
					  <form action="CancelRequest" method="post">
					  	<input type="hidden" name="cancelTo" value="<%=usr.getUserName() %>">
					    <input type="submit" value="Cancel Request" />
					   </form>
					 </div>
				<% } else {%>
					<div class="form">
					  <form action="AddFriend" method="post">
					  	<input type="hidden" name="messageTo" value="<%=usr.getUserName() %>">
					    <input type="submit" value="Add Friend" />
					   </form>
					 </div>
				<% } %>
				
				
				<a href=<%= "showFriends.jsp?profile=" +  usr.getUserName() %>> Friends </a><br>
				<a href=<%= "showHistory.jsp?profile=" +  usr.getUserName() %>> History </a><br>
				<a href="society.jsp"> Society </a><br>
			</nav>
		<% } %>
			
			<section>
				<div id="content">
					<br> 
					<% if(frs.size() == 0) {  %>
						<h2><%= username + " has no friends." %> </h2>
					<% } else { %>
						<h2> <%= username + "'s Friend List" %></h2>
						<% for (int i = 0; i < frs.size(); i++) { %>
							<%User f =  udao.getUserById(frs.get(i).getFriendId());
							String f_name = f.getUserName(); %>
							
							<div class="friends"> <a href= <%="profile.jsp?profile=" +  f_name %>> f_name </a><br>
								<a href= <%="profile.jsp?profile=" + f_name %>>
								<img src=<%=f.getUserpic() %> alt=<%= f_name %> 
								style="width:70px;height:70px;"> </a>
							</div>
								
							<% } %>
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
