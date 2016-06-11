<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Friends</title>
</head>
<body>
	<%@ page import="java.util.List" %>
	<%@ page import="user.bean.*" %>
	<%@ page import="java.util.ArrayList" %>
	
	<div id="features-wrapper">
			<div class="container">
				<div class="row">
					<div class="4u 12u(medium)">
							<!-- Box -->
								<section class="box feature">
									<div class="inner">
										<header>
												<% ArrayList<User> fr = (ArrayList<User>)session.getAttribute("friends"); %>
												<% if(fr.size() == 0) { %>
													<h1> You have no friends. </h1>
												<% } else { %>
													<h1><center>Friends</center></h1>
													<% for (int i = 0; i < fr.size(); i++) { %>
														<a href = <%= "user_profile.jsp?profile=" +  fr.get(i).getUserName()%>>
														<center><img src= <%= fr.get(i).getUserpic() %> height="50" width="50"><%="          " + fr.get(i).getUserName() %></center></a>
													<% } %>
												<% } %>
										</header>
									</div>
								</section>
					</div> 
				</div>
			</div>
	</div>
	<% session.removeAttribute("getfriends"); %>
	<% session.removeAttribute("friends"); %>
</body>
</html>

