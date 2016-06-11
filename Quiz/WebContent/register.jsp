<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register form</title> 
</head>

<body>
  <div class="login-wrap">
  <h2>Sign up</h2>
   <% if(session.getAttribute("wrongdata") != null) { %>
	<p style="color:red" > <i>
  			<font size="2"> <%= session.getAttribute("wrongdata") %> </font> 
  		</i> </p>
  		<% session.removeAttribute("wrongdata"); %>
  <% } %>
  <% if(session.getAttribute("userexists") != null) { %>
	<p style="color:red" > 
		<i>
  			<font size="2"> Such user already exists. Please, choose another. </font> 
 	 	</i>
 	</p>
  		<% session.removeAttribute("userexists"); %>
  <% } %>
  
  <div class="form">
      <form action="Register" method="post">
	    <input type="text" placeholder="Username" name="user" />
	    <input type="password" placeholder="Password" name="pass" />
	    <input type="text" placeholder="Email" name="email" />
	    <input type="text" placeholder="Photo" name="photo" />
    	<button>Create</button>
    </form>
  </div>
  
  <a href="index.jsp">Home page</a>
</div>
</body>
</html>