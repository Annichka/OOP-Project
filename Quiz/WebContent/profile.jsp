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
	<%@ page import="user.bean.*" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="manager.*" %>
	<%@ page import="user.dao.*" %>
	<%@ page import="javax.servlet.ServletContext" %>
	<%@ page import="java.io.IOException" %>
	<%@ page import="java.sql.SQLException" %>

	<% 
	Integer logged_user_id = (Integer) session.getAttribute("id");
	UserDao uDao = ((UserManager)getServletContext().getAttribute("userM")).getPersonDao();
	FriendsDao fDao = ((FriendManager)getServletContext().getAttribute("friM")).getFriendDao();
	User curr_user = uDao.getUserByName(request.getParameter("profile"));
	boolean areFriends = fDao.isFriend(logged_user_id, curr_user.getUserId());
	boolean isRequested = fDao.isRequested(logged_user_id, curr_user.getUserId());
	boolean reverseRequested = fDao.isRequested(curr_user.getUserId(), logged_user_id);
	%>
	
	<header>
		<a href="index.jsp">Quiz Web Site</a>
	</header>

	<% if(logged_user_id == curr_user.getUserId()) { %>
		<jsp:forward page = "index.jsp" />
	<% } %>
	
	<% /* მეგობრობა */ %>
	<% if (areFriends) { %>
		<nav>
			<p style="color:blue;"> <a href=<%= "profile.jsp?profile=" + request.getParameter("profile") %>>
				<%= request.getParameter("profile") + "'s Profile" %></a></p>
				
			<img src="<%= curr_user.getUserpic() %>" alt="<%= curr_user.getUserName()%>" style="width:90px;height:90px;"/>
			<div class="form">
				<form action="Unfriend" method="post">
					<input type="hidden" name="unfriendTo" value="<%=curr_user.getUserName() %>">
				    <input type="submit" value="Unfriend" />
				</form>
			</div>
			
			<a href=<%= "showFriends.jsp?profile=" +  curr_user.getUserName() %>> Friends </a><br>
			<a href=<%= "showHistory.jsp?profile=" +  curr_user.getUserName() %>> History </a><br>
			<a href="society.jsp"> Society </a><br>
		</nav>
	<% } else if (reverseRequested){%>
			<nav>		
			<p style="color:blue;"> <a href=<%= "profile.jsp?profile=" + request.getParameter("profile") %>>
				<%= request.getParameter("profile") + "'s Profile" %></a></p>
			
			<img src="<%= curr_user.getUserpic() %>" alt="<%= curr_user.getUserName()%>" style="width:90px;height:90px;"/>

			<div class="form">
			  <form action="AcceptRequest" method="post">
			  	<input type="hidden" name="acceptfrom" value="<%=curr_user.getUserName() %>">
			  	<input type="hidden" name="returntouser" value="<%=curr_user.getUserName() %>">
			    <input type="submit" value="Accept Request" />
			   </form>
			</div>
			
			<a href=<%= "showFriends.jsp?profile=" +  curr_user.getUserName() %>> Friends </a><br>
			<a href=<%= "showHistory.jsp?profile=" +  curr_user.getUserName() %>> History </a><br>
			<a href="society.jsp"> Society </a><br>
		</nav>
				
	<% } else { %>
		<nav>		
			<p style="color:blue;"> <a href=<%= "profile.jsp?profile=" + request.getParameter("profile") %>>
				<%= request.getParameter("profile") + "'s Profile" %></a></p>
			
			<img src="<%= curr_user.getUserpic() %>" alt="<%= curr_user.getUserName()%>" style="width:90px;height:90px;"/>

			<% if (isRequested) { %>
				<div class="form">
				  <form action="CancelRequest" method="post">
				  	<input type="hidden" name="cancelTo" value="<%=curr_user.getUserName() %>">
				    <input type="submit" value="Cancel Request" />
				   </form>
				 </div>
			<% } else {%>
				<div class="form">
				  <form action="AddFriend" method="post">
				  	<input type="hidden" name="messageTo" value="<%=curr_user.getUserName() %>">
				    <input type="submit" value="Add Friend" />
				   </form>
				 </div>
			<% } %>
			
			
			<a href=<%= "showFriends.jsp?profile=" +  curr_user.getUserName() %>> Friends </a><br>
			<a href=<%= "showHistory.jsp?profile=" +  curr_user.getUserName() %>> History </a><br>
			<a href="society.jsp"> Society </a><br>
		</nav>
	<% } %>

	<section>
	<br>
	<br>
	<div class="content">
		<h2> Send Message</h2>
		<form action="SendNote" method="Post">
			<textarea placeholder="Write message..." rows=3 cols=25 name="note"></textarea><br>
		    <input type="hidden" name="user" value=<%= curr_user.getUserName() %> />
		    <button> Send note </button>
		</form>
	</div>
	
	</section>
	
	<footer>
	<a href="index.jsp">Home page</a>
	</footer>
</body>
</html>