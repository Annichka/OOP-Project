<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="mystyles.css" />
	<script type="text/javascript">
		function newPopup(url) {
			popupWindow = window.open(
				url,'popUpWindow','height=30px, width=50px,left=250,top=150,resizable=no,status=yes')
		}
	</script>
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
	
	<% /* Get requested user object */ %>
	
	<% 
	Integer logged_user_id = (Integer) request.getServletContext().getAttribute("id");
	UserDao uDao = ((UserManager)getServletContext().getAttribute("userM")).getPersonDao();
	FriendsDao fDao = ((FriendManager)getServletContext().getAttribute("friM")).getFriendDao();
	User curr_user = uDao.getUserByName(request.getParameter("profile"));
	boolean areFriends = fDao.isFriend(logged_user_id, curr_user.getUserId());
	%>
	
	<%
	/* User-ის სახელი, სურათი
	 * მეგობრობის რექვესთის გაგზავნა - თუ მეგობარია Friend დაეწეროს.
	 * გაკეთებული ქვიზები და ქულები
	 * მეგობრები
	*/
	%>
	
	<% /* User-ის სახელი, სურათი */ %>
	<header>
		<%= request.getParameter("profile") %>'s Profile
	</header>
	
	<img src="<%= curr_user.getUserpic() %>" alt="<%= curr_user.getUserName()%>" style="width:90px;height:90px;">
				
	<% /* მეგობრობა */ %>
	<% if (areFriends) { %>
		<p> Friends </p>
	<% } else { %>
		<div class="form">
			<form action="AddFriend" method="post">
				<button> Add Friend </button>
			</form>
		</div>
	<% } %>


<footer>
<a href="index.jsp">Home page</a>
</footer>

</body>
</html>