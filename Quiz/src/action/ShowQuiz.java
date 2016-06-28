package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.Question;
import quiz.dao.QuestionDao;
import quiz.dao.QuizDao;

/**
 * Servlet implementation class showQuiz
 */
@WebServlet("/ShowQuiz")
public class ShowQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowQuiz() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager uM = (UserManager)getServletContext().getAttribute("userM");
		int quizId = (Integer)getServletContext().getAttribute("quizprocess");
		QuestionDao qDao = uM.getQuestionDao();
		try {
			ArrayList<Question> quesList = qDao.getQuestionsByQuizId(quizId);
			request.setAttribute("questionlist", quesList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("showQuiz.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
