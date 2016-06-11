<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Login & Register</title>
  </head>
  
  <body>

  <div class="login-wrap">
  <h2>Login</h2>
  <% if(session.getAttribute("registered") != null) { %>
  		<p style="color:blue" > <i>
  			<font size="2">You have registered successfully. Please log in. </font> 
  		</i> </p>
  		<% session.removeAttribute("registered"); %>
  <% } %>
  <% if(session.getAttribute("wronguser") != null) { %>
  		<p style="color:red" > <i>
  			<font size="2">User name or Password is incorrect. Please try again. </font> 
  		</i> </p>
  		<% session.removeAttribute("wronguser"); %>
  <% } %>
  
  <div class="form">
  <form action="Login" method="get">
    <input type="text" placeholder="Username" name="user" />
    <input type="password" placeholder="Password" name="pass" />
    <button> Sign in </button>
    <p> <a href="register.jsp"> Don't have an account? Register </a> </p>
   </form>
  </div>
  
  <a href="index.jsp">Home page</a>
</div>
</body>
</html>