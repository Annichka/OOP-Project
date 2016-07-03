package action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import user.bean.User;
import user.dao.UserDao;

/**
 * Servlet implementation class AdminUser
 */
@WebServlet("/AdminUser")
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = (UserManager) getServletContext().getAttribute("userM");
		UserDao userDao = userManager.getPersonDao();
		String finalHtml = "";
		try {
			List<User> users = userDao.allUsers();
			String path = getServletContext().getRealPath("templates/user_template.html");
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String userHtml = "";
			String line;
			while ((line = reader.readLine()) != null) {
				userHtml += line;
			}
			for (User user: users) {
				line = new String(userHtml);
				line = line.replace("[[:user_id:]]", "" + user.getUserId());
				line = line.replace("[[:user_name:]]", user.getUserName());
				line = line.replace("[[:user_email:]]", user.getEMail());
				line = line.replace("[[:user_priority:]]", "" + user.getPriority());
				finalHtml += line;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(finalHtml);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Received request for " + request.getParameter("user_id"));
	}

}
