<%@page import="org.omg.CORBA.SystemException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	
	<h2> All Users </h2>
	<% try { %>
		<% ServletContext cont = request.getServletContext(); %>
		<% ArrayList<User> all_user = (ArrayList<User>) cont.getAttribute("alluser");  %>
		<% for (int i = 0; i < all_user.size(); i++) { %>
			<h><a href= <%= "user_profile.jsp?profile=" +  all_user.get(i).getUserName()%>>
				<small> <%= all_user.get(i).getUserName() %></small>
				<p><img src="<%= all_user.get(i).getUserpic() %>" alt="<%= all_user.get(i).getUserName() %>" style="width:70px;height:70px;">
			</p></a></h>
		<% } %> 
	<%} catch (NullPointerException e) { %>
		<a> Nobody </a>
	<%}%>

</body>
</html>
