package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.FriendManager;
import manager.UserManager;
import user.bean.Friends;
import user.bean.User;

/**
 * Servlet implementation class Friendship
 */
@WebServlet("/Friendship")
public class Friendship extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friendship() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    	ServletContext sCont = request.getServletContext();
    	String user = (String) sCont.getAttribute("username");
	    UserManager usrM = null;
	    FriendManager frM = null;
	    User me = null;
	    List<Friends> friends = new ArrayList<Friends>();
	    List<User> friend_list = new ArrayList<User>();

	    try {
			usrM = (UserManager) sCont.getAttribute("userM");
			me = usrM.getPersonDao().getUserByName(user);
			frM = (FriendManager) sCont.getAttribute("friM");
			friends = frM.getFriendDao().getFriendList(me.getUserId());
			for(int i = 0; i < friends.size(); i++) {
				User curr = new User();
				curr = usrM.getPersonDao().getUserById(friends.get(i).getFriendId());
				friend_list.add(curr);
			}
			HttpSession session = request.getSession();
			session.setAttribute("getfriends", true);
	        session.setAttribute("friends", friend_list);
	        response.sendRedirect("index.jsp");  
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
