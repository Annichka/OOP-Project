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
 * Servlet implementation class StartCreating
 */
@WebServlet("/StartCreating")
public class StartCreating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartCreating() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (getServletContext().getAttribute("quizprocess") != null)
			getServletContext().removeAttribute("quizprocess");
		
		UserManager uM =  (UserManager) getServletContext().getAttribute("userM");
		QuizDao qzDao = uM.getQuizDao();
		String quizName = request.getParameter("quizName");
		String category = request.getParameter("category");
		int isRandom = -1;
		int isFinished = 0;
		String[] random= request.getParameterValues("isRandom");
	    
	    if(random == null) {
	    	isRandom = 0;
	    } else 
	    	isRandom = 1;
		Integer authorId = (Integer)getServletContext().getAttribute("id");
		String quizid = "" + qzDao.createNewQuiz(quizName, authorId, category, isRandom, isFinished);
		getServletContext().setAttribute("quizprocess", quizid);
			
		response.sendRedirect("startQuestionTypes.jsp"); //gadasvla questenebis gasaketebel page-ze
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
