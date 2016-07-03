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
		String html = "";
		try {
			List<User> users = userDao.allUsers();
			String path = getServletContext().getRealPath("templates/user_template.html");
			if (request.getParameter("user_id") != null) {
				path = getServletContext().getRealPath("templates/user_edit.html");
			}
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String userHtml = "";
			String line;
			while ((line = reader.readLine()) != null) {
				userHtml += line;
			}
			if (request.getParameter("user_id") == null) {
				for (User user: users) {
					line = new String(userHtml);
					line = line.replace("[[:user_id:]]", "" + user.getUserId());
					line = line.replace("[[:user_name:]]", user.getUserName());
					line = line.replace("[[:user_email:]]", user.getEMail());
					line = line.replace("[[:user_priority:]]", "" + user.getPriority());
					html += line;
				}
			} else {
				int index = Integer.parseInt(request.getParameter("user_id"));
				User user = null;
				for (User current: users) {
					if (current.getUserId() == index) {
						user = current;
						break;
					}
				}
				html = userHtml;
				html = html.replace("[[:user_id:]]", "" + user.getUserId());
				html = html.replace("[[:user_name:]]", user.getUserName());
				html = html.replace("[[:user_email:]]", user.getEMail());
				html = html.replace("[[:user_priority:]]", "" + user.getPriority());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = (UserManager) getServletContext().getAttribute("userM");
		UserDao userDao = userManager.getPersonDao();
		
		String action = request.getParameter("action");
		int user = Integer.parseInt(request.getParameter("user_id"));
		try {
			if (action.equals("Remove Account")) {
				userDao.removeAccount(user);
				String html = "<div class='message thank-message'>";
				html += String.format("<p> <strong> User %s has been removed </p> </strong>", user);
				html += "</div>";
				response.getWriter().write(html);
				return;
			}
			if (action.equals("Save Changes")) {
				User item = userDao.getUserById(user);
				String email = request.getParameter("user_email");
				if (email == null || email.equals("")) {
					email = item.getEMail();
				}
				String name = request.getParameter("user_name");
				if (name == null || name.equals("")) {
					name = item.getUserName();
				}
				String priorityString = request.getParameter("user_priority");
				if (priorityString == null || priorityString.equals("")) {
					priorityString = "" + item.getPriority();
				}
				int adminID = (int) request.getSession().getAttribute("id");
				User admin = userDao.getUserById(adminID);
				if (admin.getPriority() >= item.getPriority()) {
					item.setEMail(email);
					item.setUserName(name);
					item.setPriority(Integer.parseInt(priorityString));
					item.setUserId(user);
					userDao.updateUser(user, item);
					String html = "<div class='message thank-message'>";
					html += String.format("<p> <strong> User %s has been updated </p> </strong>", user);
					html += "</div>";
					response.getWriter().write(html);
					return;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String html = "<div class='message error-message'>";
		html += "<p> <strong> Action couldn't be completed </p> </strong>";
		html += "</div>";
		response.getWriter().write(html);
		return;
	}

}
