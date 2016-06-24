<!DOCTYPE html>
<html>
<head>
<style>
header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
nav {
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:right;
    padding:5px;
}
section {
    width:350px;
    float:left;
    padding:10px;
}
footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;
}
</style>
</head>
<body>

<header>
<h1>Quiz Web Site</h1>
</header>
		<%if (session.getAttribute("authorized") != null) { %>
			<section>
			<%if (session.getAttribute("getfriends") != null) { %>
				<%@ include file = "friend_list.jsp" %>
				<% session.removeAttribute("getfriends"); %>
				<% session.removeAttribute("friends");  %>
			<%} %>
			<% ServletContext sCont = request.getServletContext(); %>
				<h1><a href="index.jsp">
					<%= sCont.getAttribute("username") %>'s Wall
				</a></h1>
				<img src="<%= sCont.getAttribute("image") %>" alt="<%= sCont.getAttribute("username") %>" style="width:90px;height:90px;">
				
				<section class="widget links">
					<ul class="style2">
						<li><a href="Friendship">Friend List</a></li>
					</ul>
				</section>
				
				<div class="form">
				  <form action="Logout" method="get">
				    <button> Logout </button>
				  </form>
				</div>
				
				<%@ include file = "user_list.jsp" %> 
		</section>

		<% } else { %>
			<nav>
			<a href="login.jsp"  >Login</a> <br>
			<a href="register.jsp" >Register</a>
			</nav>
		<% } %>

<footer>
<a href="index.jsp">Home page</a>
</footer>

</body>
</html>
