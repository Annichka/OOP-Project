<%@page import="org.omg.CORBA.SystemException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%@ page import="java.util.List" %>
	<%@ page import="user.bean.*" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="manager.*" %>
	<%@ page import="user.dao.*" %>
	<%@ page import="java.io.IOException" %>
	<%@ page import="java.sql.SQLException" %>
	<%@ page import ="javax.servlet.ServletContext" %>
	<%@ page import ="java.util.*" %>
	
	<h2> Users </h2>
	<% try { %>
		<% ArrayList<User> all_user = (ArrayList<User>) session.getAttribute("alluser");  %>
		<% Collections.shuffle(all_user); %>
		<% for (int i = 0; i < all_user.size(); i++) { %>
			<% if (i > 10) break; %>
			<div class="friends">
				<a href=<%= "profile.jsp?profile=" +  all_user.get(i).getUserName()%>><%= all_user.get(i).getUserName() %></a><br>
			    <a href=<%= "profile.jsp?profile=" +  all_user.get(i).getUserName()%>>
			    <img src="<%= all_user.get(i).getUserpic() %>" alt="<%= all_user.get(i).getUserName() %>" 
			    style="width:70px;height:70px;"></a>
			</div>	
		<% } %>
	<%} catch (NullPointerException e) { %>
		<a> Nobody </a>
	<%}%>

</body>
</html>
