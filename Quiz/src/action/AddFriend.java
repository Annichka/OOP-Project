package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MessageManager;
import manager.UserManager;
import user.bean.Messages;
import user.bean.User;
import user.dao.MessagesDao;
import user.dao.UserDao;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
        super();
        // TODO Auto-generated constructor stub
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
		ServletContext sCont = request.getServletContext();
		try {
			String name = (String) sCont.getAttribute("username");
			User me = usrD.getUserByName(name);
			String fr_name =  (String) request.getParameter("messageTo");
			User user = usrD.getUserByName(fr_name);
			
			String text = "You Have Friend Request from " + me.getUserName();
			String type = "friendrequest";
			SendFriendRequest(me, user, text, type);
		
			response.sendRedirect("profile.jsp?profile=" + user.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void SendFriendRequest(User from, User to, String text, String type) throws SQLException {
		MessageManager msgM = (MessageManager) getServletContext().getAttribute("mesM");
		MessagesDao msgD = msgM.getMessageDao();
		Messages sms = new Messages();
		
		sms.setSender(from.getUserId());
		sms.setReceiver(to.getUserId());
		sms.setMessage(text);
		sms.setMType(type);
		sms.setQuizId(-1);
		
		msgD.addFriendRequest(sms);
	}
}
