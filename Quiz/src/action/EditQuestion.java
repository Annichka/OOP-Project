package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.MultiAnswer;
import quiz.bean.MultipleChoice;
import quiz.bean.PictureResponse;
import quiz.bean.Question;
import quiz.dao.QuestionDao;

/**
 * Servlet implementation class SubmitQuestion
 */
@WebServlet("/EditQuestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		 * Question editing during test view.
		 * Redirects to main page, where is shown all questions.
		 * 
		 * */
		
		UserManager uM = (UserManager) getServletContext().getAttribute("userM");
		QuestionDao qDao = uM.getQuestionDao();
		
		String qst = (String) request.getParameter("quest");
		String answ = (String) request.getParameter("cansw");
		int qstid = Integer.parseInt((String)request.getParameter("questid"));
		String wansw = "";
		int ordered = 0;
		int answCount = Integer.parseInt((String)request.getParameter("correctC"));
		
		Question q = qDao.getQuestionById(qstid);
		q.setQuestion(qst);
		q.setCAnswer(answ);
		q.setAnswerCount(answCount);
		
		if(q.getType().equals("PR")) {
			((PictureResponse) q).setPicUrl(request.getParameter("picurl"));
		} else if(q.getType().equals("MC") || q.getType().equals("MCA")) {
			((MultipleChoice) q).setWAnswers(wansw);
			((MultipleChoice) q).setOrdered(ordered);
		} else if(q.getType().equals("MA")) {
			((MultiAnswer) q).setIsOrdered(ordered);
		}
		
		qDao.updateQuestio(q);
		
		int quizId = q.getQuizId();
		
		
		ArrayList<Question> quesList;
		try {
			quesList = qDao.getQuestionsByQuizId(quizId);  		
			request.setAttribute("questionlist", quesList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// wavides mtliani quizis naxvaze
		
		response.sendRedirect("showQuiz.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
