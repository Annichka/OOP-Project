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

	<% if (getServletContext().getAttribute("formid") != null) %>
		<% getServletContext().removeAttribute("formid"); %>
		
	<% 
	Integer logged_user_id = (Integer) request.getServletContext().getAttribute("id");
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

	<% /* მეგობრობა */ %>
	<% if (areFriends) { %>
		<nav>
			<p style="color:blue;"><%= request.getParameter("profile") %>'s Profile</p>
			<img src="<%= curr_user.getUserpic() %>" alt="<%= curr_user.getUserName()%>" style="width:90px;height:90px;"/>
			<div class="form">
				<form action="Unfriend" method="post">
					<input type="hidden" name="unfriendTo" value="<%=curr_user.getUserName() %>">
				    <input type="submit" value="Unfriend" />
				</form>
			</div>
			
			<input type="button" value="Friends" name= <%= curr_user.getUserName() %> onClick="friendList(this)"><br>
			<a href="History?username">History</a><br>
		</nav>
	<% } else if (reverseRequested){%>
			<nav>		
			<p style="color:blue;"><%= request.getParameter("profile") %>'s Profile</p>
			
			<img src="<%= curr_user.getUserpic() %>" alt="<%= curr_user.getUserName()%>" style="width:90px;height:90px;"/>

			<div class="form">
			  <form action="AcceptRequest" method="post">
			  	<input type="hidden" name="acceptfrom" value="<%=curr_user.getUserName() %>">
			  	<input type="hidden" name="returntouser" value="<%=curr_user.getUserName() %>">
			    <input type="submit" value="Accept Request" />
			   </form>
			</div>
			
			<input type="button" value="Friends" name= <%= curr_user.getUserName() %> onClick="friendList(this)"><br>
			<a href="History?username">History</a><br>
		</nav>
				
	<% } else { %>
		<nav>		
			<p style="color:blue;"><%= request.getParameter("profile") %>'s Profile</p>
			
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
			
			
			<input type="button" value="Friends" name= <%= curr_user.getUserName() %> onClick="friendList(this)"><br>
			<a href="History?username">History</a><br>
		</nav>
	<% } %>

	<section>
	<br>
	<br>
	<div class="content">
		<form action="SendNote" method="Post">
		    <input type="text" placeholder="Write message..." name="note" />
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