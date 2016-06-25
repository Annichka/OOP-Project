package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.bean.User;
import user.dao.FriendsDao;
import user.dao.UserDao;
import manager.FriendManager;
import manager.UserManager;

/**
 * Servlet implementation class DeleteRequest
 */
@WebServlet("/CancelRequest")
public class CancelRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req_to = (String) request.getParameter("cancelTo");
		String me = (String) getServletContext().getAttribute("username");
		UserManager usrM = (UserManager) getServletContext().getAttribute("userM");
		UserDao usrD = usrM.getPersonDao();

		FriendManager friendM = (FriendManager) getServletContext().getAttribute("friM");
		FriendsDao friendD = friendM.getFriendDao();
		
		User u_from = null;
		User u_to = null;
		/* Message-ebidan shlis FriendRequest mesijs.*/
		try {
			u_from = usrD.getUserByName(me);
			u_to = usrD.getUserByName(req_to);
			
			friendD.deleteRequest(u_from, u_to);
			
			response.sendRedirect("profile.jsp?profile=" + req_to);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}