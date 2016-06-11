<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

<head>

<script type="text/javascript">
	function newPopup(url) {
		popupWindow = window.open(
			url,'popUpWindow','height=30px, width=50px,left=250,top=150,resizable=no,status=yes')
	}
</script>

		<title>Quiz Web site</title>
		<meta charset="utf-8" />
		
</head>

	<body class="homepage">
		<div id="page-wrapper">
		
		<% if (session.getAttribute("authorized") != null) { %>
				<!-- Logo -->
				<header id="header" class="container">
					<div id="logo">
						<font size = "1">
							<h1><a href="index.jsp">
								<%= session.getAttribute("username") %>'s Wall
							</a></h1>
						</font>
					</div>
				</header>
				
				<div class="form">
				  <form action="Logout" method="get">
				    <button> Logout </button>
				  </form>
				</div>
       	<% } else { %>				
		<!-- Banner -->
				<div id="banner-wrapper">
					<div id="banner" class="box container">
						<div class="row">
							<div class="7u 12u(medium)">
								<h2>Hello. This is Quiz Web site.</h2>
							</div>
							<div class="5u 12u(medium)">
								<ul>
									<li><a href="login.jsp" class="button big icon fa-arrow-circle-right">Login</a></li>
									<li><a href="register.jsp" class="button alt big icon fa-question-circle">Register</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			<% } %>

			
			<!-- Footer -->
				<div id="footer-wrapper">
					<footer id="footer" class="container">
						<div class="row">
							<div class="3u 6u(medium) 12u$(small)">

								<!-- Links -->
									<section class="widget links">
										<ul class="style2">
											<li><a href="index.jsp">Home page</a></li>
										</ul>
									</section>

							</div>
							
							<div class="3u 6u$(medium) 12u$(small)">

								<!-- Contact -->
									<section class="widget contact last">
										<h3>Contact Us</h3>
										<ul>
											<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
											<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
										</ul>
									</section>

							</div>
						</div>
	
					</footer>
				</div>

			</div>

	</body>
</html>