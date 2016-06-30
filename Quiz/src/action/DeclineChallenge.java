package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MessageManager;
import user.dao.MessagesDao;

/**
 * Servlet implementation class DeclineChallenge
 */
@WebServlet("/DeclineChallenge")
public class DeclineChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeclineChallenge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chall_id = (String) request.getParameter("challengeId");

		MessageManager msgM = (MessageManager) getServletContext().getAttribute("mesM");
		MessagesDao msgD = msgM.getMessageDao();
	
		int challid = Integer.parseInt(chall_id);
		
		try {
			msgD.deleteMessageById(challid);
			
			response.sendRedirect("myChallenges.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
