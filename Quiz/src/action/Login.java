package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DataBase;
import manager.*;
import user.bean.*;
import user.dao.*;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getParameter("user");
		String password = (String)request.getParameter("pass");
		User acc = new User();		// Input user object
		acc.setUserName(user);
		acc.GenerateHashedPassword(password);
		try {
			DataBase db = new DataBase();
			DataBase.db = db;
			UserManager usrM = (UserManager) getServletContext().getAttribute("userM");
			UserDao dao = usrM.getPersonDao();
			FriendsDao fDao = (new FriendManager(DataBase.db)).getFriendDao();
			User found_acc = dao.getUserByName(acc.getUserName());
			if (found_acc != null && found_acc.getHashedPassword().equals(acc.getHashedPassword())) {
				HttpSession session = request.getSession();
		        session.setAttribute("authorized", true);
				ServletContext sCont = request.getServletContext();
				sCont.setAttribute("username", user);
				sCont.setAttribute("image", found_acc.getUserpic());
				sCont.setAttribute("id", found_acc.getUserId());
				
				/* All User List */
				ArrayList<User> all_user = (ArrayList<User>) dao.allUserExcept(found_acc.getUserId());
				sCont.setAttribute("alluser", all_user);
				
		        response.sendRedirect("index.jsp");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("wronguser", true);
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
