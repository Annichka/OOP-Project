package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.dao.QuizDao;

/**
 * Servlet implementation class FinishQuizCreating
 */
@WebServlet("/FinishQuizCreating")
public class FinishQuizCreating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishQuizCreating() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = (String) getServletContext().getAttribute("quizprocess");
		Integer quizid = Integer.parseInt(q);
		
		UserManager uM = (UserManager)getServletContext().getAttribute("userM");
		QuizDao qzDao = uM.getQuizDao();
		qzDao.setQuizFinished(quizid);
		
		getServletContext().removeAttribute("quizprocess");
		request.getSession().setAttribute("quizfinished", true);
		response.sendRedirect("createQuiz.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
