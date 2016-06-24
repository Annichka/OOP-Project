package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.FriendManager;
import manager.UserManager;
import user.bean.User;
import user.dao.FriendsDao;
import user.dao.UserDao;

/**
 * Servlet implementation class AcceptRequest
 */
@WebServlet("/AcceptRequest")
public class AcceptRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptRequest() {
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
		String req_from = (String) request.getParameter("acceptfrom");
		String req_to = (String) getServletContext().getAttribute("username");
		UserManager usrM = (UserManager) getServletContext().getAttribute("userM");
		UserDao usrD = usrM.getPersonDao();

		FriendManager friendM = (FriendManager) getServletContext().getAttribute("friM");
		FriendsDao friendD = friendM.getFriendDao();
		User u_from = null;
		User u_to = null;
		
		try {
			u_from = usrD.getUserByName(req_from);
			u_to = usrD.getUserByName(req_to);
			friendD.acceptRequest(u_from, u_to);
			
			
			HttpSession session = request.getSession();
			session.setAttribute("friendrequests", true);
			response.sendRedirect("index.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
