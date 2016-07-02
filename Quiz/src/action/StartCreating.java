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
		/* 
		 * Servlet is called after clicking Start Quiz Create button.
		 * Redirects to Types-Page.
		 * 
		 * */
		if (getServletContext().getAttribute("quizprocess") != null)
			getServletContext().removeAttribute("quizprocess");
		
		UserManager uM =  (UserManager) getServletContext().getAttribute("userM");
		QuizDao qzDao = uM.getQuizDao();
		String quizName = request.getParameter("quizName");
		String category = request.getParameter("category");
		String description =request.getParameter("description");
		
		int isRandom = -1;
		int isFinished = 0;
		int isMultiPage = -1;
		String[] random = request.getParameterValues("isRandom");
	    String[] multiP = request.getParameterValues("multiPage");
	    if(random == null) {
	    	isRandom = 0;
	    } else 
	    	isRandom = 1;
	    
	    if(multiP == null) {
	    	isMultiPage = 0;
	    } else 
	    	isMultiPage = 1;
	    
		Integer authorId = (Integer)getServletContext().getAttribute("id");
		String quizid = "" + qzDao.createNewQuiz(quizName, description, authorId, category, isRandom, isFinished, isMultiPage);
		
		getServletContext().setAttribute("quizprocess", quizid);
		response.sendRedirect("startQuestionTypes.jsp?quizid=" + quizid); //gadasvla questenebis gasaketebel page-ze
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
