package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.FillInTheBlank;
import quiz.bean.MultiAnswer;
import quiz.bean.MultipleChoice;
import quiz.bean.Question;
import quiz.bean.Scoring;
import quiz.dao.QuestionDao;
import quiz.dao.QuizDao;

/**
 * Servlet implementation class SumbitQuiz
 */
@WebServlet("/SubmitQuiz")
public class SubmitQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitQuiz() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Servlet for user, who filled chosen quiz.
		 * Saves user's history, score, quiz_data.
		 *  */
		System.out.println("MODIS AQ?");
		UserManager um = (UserManager) getServletContext().getAttribute("userM");
		QuizDao qd = um.getQuizDao();
		QuestionDao qsd = um.getQuestionDao();
	
		int qid = Integer.parseInt((String) request.getParameter("quizid"));
		ArrayList<Question> qstlist = null;
		try {
			qstlist = qsd.getQuestionsByQuizId(qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int quest_count = qstlist.size();
		ArrayList<ArrayList<String>> answers = new ArrayList<>();
		
		for(int i=0; i<quest_count; i++) {
			String ans = "";
			Question q = qstlist.get(i);
			if(q.getType().equals("QR")) 
			{
				ArrayList<String> s = new ArrayList<>();
				ans = (String)request.getParameter(i + "");
				System.out.println(ans);
				s.add(ans);
				answers.add(s);
			} 
			else if(q.getType().equals("FB")) 
			{
				FillInTheBlank q2 = (FillInTheBlank)q;
				ArrayList<String> s = new ArrayList<>();
				int count = q2.getAnswerCount();
				for(int j=0; j<count; j++) {
					ans = (String)request.getParameter(i + "x" + j);
					s.add(ans);
				}
				answers.add(s);
			}
			else if(q.getType().equals("PR")) 
			{
				ArrayList<String> s = new ArrayList<>();
				ans = (String)request.getParameter(i + "");
				System.out.println(ans);
				s.add(ans);
				answers.add(s);
			}
			else if(q.getType().equals("MC")) 
			{
				ArrayList<String> s = new ArrayList<>();				
				ans = request.getParameter(i + "");
				s.add(ans);
				answers.add(s);
			}
			else if(q.getType().equals("MCA")) 
			{
				MultipleChoice q2 = (MultipleChoice)q;
				ArrayList<String> s = new ArrayList<>();
				int count = q2.countCorrectAnswers() + q2.countWrongAnswers();
				for(int j=0; j<count; j++) {
					ans = request.getParameter(i + "x" + j);
					s.add(ans);
				}
				answers.add(s);
			}
			else if(q.getType().equals("MA")) 
			{
				MultiAnswer q2 = (MultiAnswer)q;
				ArrayList<String> s = new ArrayList<>();
				int count = q2.getAnswerCount();
				for(int j=0; j<count; j++) {
					ans = request.getParameter(i + "x" + j);
					s.add(ans);
				}
				answers.add(s);
			}
		}
		
		Scoring sc = new Scoring();
		int score = sc.countForQuiz(qstlist, answers);
		
		System.out.println("SCORE FOR QUIZ     = = = =     " + score);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
