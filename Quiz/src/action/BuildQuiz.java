package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuildQuiz
 */
@WebServlet("/BuildQuiz")
public class BuildQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildQuiz() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		String qtype = "";
		if(type.equals("QR")){
			qtype = "?action=ques_resp";
		} else if (type.equals("FB")){
			qtype = "?action=fill";
		} else if (type.equals("MC")){
			qtype = "?action=mult_choi";
		} else if(type.equals("PR")){
			qtype = "?action=pic_resp";
		} else if(type.equals("MA")){
			qtype = "?action=mult_answ";
		} else if(type.equals("MCA")){
			qtype = "?action=mult_choi";
		} else if(type.equals("M")){
			qtype = "?action=match_quest";
		}
		
		response.sendRedirect("createQuiz.jsp" + qtype);
		getServletContext().setAttribute("type", type);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
