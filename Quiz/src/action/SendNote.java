package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.MessageManager;
import manager.UserManager;
import user.bean.Messages;
import user.bean.User;
import user.dao.MessagesDao;
import user.dao.UserDao;

/**
 * Servlet implementation class SendNote
 */
@WebServlet("/SendNote")
public class SendNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendNote() {
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
		StringBuffer text = new StringBuffer(request.getParameter("note"));
		
	    String msg_to = (String) request.getAttribute("noteTo");
		String from_me = (String) getServletContext().getAttribute("username");
		
		request.getSession().removeAttribute("prof_n");
		MessageManager msgM = (MessageManager) getServletContext().getAttribute("mesM");
		MessagesDao msgD = msgM.getMessageDao();
		UserManager usrM = (UserManager) getServletContext().getAttribute("userM");
		UserDao usrD = usrM.getPersonDao();
		
		User to = null;
		User from = null;
		try {
			to = usrD.getUserByName(msg_to);
			from = usrD.getUserByName(from_me);
			
			Messages msg = new Messages();
			msg.setSender(from.getUserId());
			msg.setReceiver(to.getUserId());
			msg.setMessage(new String(text));
			msg.setMType("note");
			
			msgD.addMessage(msg);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("prof_n", to.getUserName());
		
		response.sendRedirect("writeMessage.jsp");
	}

}
