<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your quiz</title>
</head>
<body>

	<%@ page import="manager.UserManager"%>
	<%@ page import="quiz.dao.QuestionDao"%>
	<%@ page import="quiz.bean.Question"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.Vector"%>


	<%
		String s = "salome; luka; shota";
		int m = 0;
		int k = 0;
		Vector<String> v = new Vector<String>();
		for (int i = 0; i < s.length(); i++) {
			while (s.charAt(i) != ';')
				k++;
			{
				String a = s.substring(m, k);
				v.add(a);
				m = k;
			}
		}
	%>
	<form action=" %" method="get">

		<table border="0">
			<tr>
				<td><input type="checkbox" name="check"
					<%for (int b = 0; b < v.size(); b++) {
				String check = v.get(b);
				//System.out.print(check);%>
					value=b /></td>
				<td>
					<%
						out.print(v.get(b));
					%>
				</td>
				
			</tr>
		</table>
						<%
					}
				%>
		<input type="submit" value="answerS" />
	</form>


</body>
</html>