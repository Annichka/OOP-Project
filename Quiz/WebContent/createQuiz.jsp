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

			<nav>
				<h2><a href="index.jsp">
					<%= session.getAttribute("username") %>
				</a></h2>
				<img src="<%= session.getAttribute("image") %>" alt="<%= session.getAttribute("username") %>" style="width:90px;height:90px;"><br>
				
				<%@ include file="panel.jsp" %>
				
				<div class="form">
				  <form action="Logout" method="get">
				    <button> Logout </button><br>
				  </form>
				</div>
			</nav>

			<section>
				<div id="content">
				<br>
				<br>
				<%if (session.getAttribute("quizfinish") != null) { %>
					<p> Your quiz is added. </p>
				<%} else if (session.getAttribute("quizprocess") != null){ %>
					
					
					<form action="ShowQuiz" method="get">
						<button>Continue Started Quiz</button>
					</form> 
					<br>
					<br>
					<p> OR </p>
					<br>
					<br>
				<%} %>
					<h3> Create your own quiz :)</h3> <br>
					<form action="StartCreating" method="post">
						<i>Quiz Name:</i> <input type="text" name="quizName"><br>
						<i>Description: </i><br>
						<textarea rows=3 cols=15 name="description"></textarea><br>
						
						<i>Category:</i> 
						<%@ include file="quizCategories.jsp" %><br>
						<i>Random Order:</i> <input type="checkbox" name="isRandom" value="random"><br>
						<i>Multiple Page:</i> <input type="checkbox" name="multiPage" value="multi"> <br>
						<i>Correction:</i> <input type="checkbox" name="correction" value="correction">
						<br><br>
						<button>Start new Quiz</button>
						
					</form>
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
</html>