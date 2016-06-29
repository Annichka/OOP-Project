package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.UserManager;
import user.bean.User;
import user.bean.UserDataChecker;
import user.dao.UserDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Register() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * User Sing Up
		 * 
		 * */
		
		String user = request.getParameter("user");
		String password = request.getParameter("pass");
		String email = request.getParameter("email");
		String pic = request.getParameter("photo");

		Hasher hash = new Hasher();
		User usr = new User();
		usr.setUserName(user);
		usr.setPassword(password);
		usr.setHashedPassword(hash.getHashedPassword(password));
		usr.setEMail(email);
		usr.setPicURL(pic);
		
		UserDataChecker chk = new UserDataChecker(usr);
		String checked = chk.Check();

		if(!checked.equals("OK")) {
			HttpSession session = request.getSession();
	        session.setAttribute("wrongdata", checked);
	        response.sendRedirect("register.jsp");
		} else {
			try {
				UserManager usrM = (UserManager) request.getServletContext().getAttribute("userM");
				UserDao dao = usrM.getPersonDao();
				if (dao.getUserByName(user) != null) {
					HttpSession session = request.getSession();
			        session.setAttribute("userexists", true);
			        response.sendRedirect("register.jsp");
				} else {
					dao.addUser(usr);
					HttpSession session = request.getSession();
			        session.setAttribute("registered", true);
			        response.sendRedirect("login.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
