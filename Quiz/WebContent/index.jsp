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
</head>
<body>

<header>
<h1>Quiz Web Site</h1>
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
			<nav>
				<% ServletContext sCont = request.getServletContext(); %>
				<h1><a href="index.jsp">
					<%= sCont.getAttribute("username") %>
				</a></h1>
				<img src="<%= sCont.getAttribute("image") %>" alt="<%= sCont.getAttribute("username") %>" style="width:90px;height:90px;">
				<a href="Friendship">Friend List</a><br>
				<a href="#">Messages</a><br>
				<a href="#">History</a><br>
				<div class="form">
				  <form action="Logout" method="get">
				    <button > Logout </button>
				  </form>
				</div>
			</nav>
		<section>
			<%if (session.getAttribute("getfriends") != null) { %>
				<%@ include file = "friend_list.jsp" %>
				<% session.removeAttribute("getfriends"); %>
				<% session.removeAttribute("friends");  %>
			<%} %>
				
		</section>
		<% } %>

<footer>
<a href="index.jsp">Home page</a>
</footer>

</body>
</html>