package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DataBase;
import manager.UserManager;
import user.bean.User;
import user.dao.UserDao;

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
			UserManager usrM = new UserManager(DataBase.db); //getServletContext().getAttribute("userM");
			UserDao dao = usrM.getPersonDao();
			User found_acc = dao.getUserByName(acc.getUserName());
			if (found_acc != null && found_acc.getHashedPassword().equals(acc.getHashedPassword())) {
				HttpSession session = request.getSession();
		        session.setAttribute("authorized", true);
		        session.setAttribute("username", user);
		        session.setAttribute("id", found_acc.getUserId());
				ServletContext sCont = request.getServletContext();
				sCont.setAttribute("user", user);
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
