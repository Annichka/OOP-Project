package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.FriendManager;
import manager.UserManager;
import user.bean.Friends;
import user.bean.User;
import user.dao.FriendsDao;
import user.dao.UserDao;

/**
 * Servlet implementation class Unfriend
 */
@WebServlet("/Unfriend")
public class Unfriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unfriend() {
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
		UserManager usrM = (UserManager) getServletContext().getAttribute("userM");
		UserDao usrD = usrM.getPersonDao();

		FriendManager friendM = (FriendManager) getServletContext().getAttribute("friM");
		FriendsDao friendD = friendM.getFriendDao();
		User me;
		User friend;
		try {
			String myname = (String) getServletContext().getAttribute("username");
			me = usrD.getUserByName(myname);
			String fr_name =  request.getParameter("unfriendTo");
			friend = usrD.getUserByName(fr_name);
			Friends fr = new Friends();
			fr.setUserId(me.getUserId());
			fr.setFriendId(friend.getUserId());
			friendD.deleteFriend(fr);
			
			response.sendRedirect("profile.jsp?profile="+friend.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
