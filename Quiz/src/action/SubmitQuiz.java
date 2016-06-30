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
import quiz.bean.FillInTheBlank;
import quiz.bean.History;
import quiz.bean.MultiAnswer;
import quiz.bean.MultipleChoice;
import quiz.bean.Question;
import quiz.bean.Scoring;
import quiz.dao.QuestionDao;
import quiz.dao.QuizDao;
import user.dao.UserDao;

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

		UserManager um = (UserManager) getServletContext().getAttribute("userM");
		UserDao ud = um.getPersonDao();
	
		String me = (String) getServletContext().getAttribute("username");
		int uid = -1;;
		try {
			uid = ud.getUserByName(me).getUserId();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int qid = Integer.parseInt((String) request.getParameter("quizid"));

		ArrayList<Question> qstlist = (ArrayList<Question>) getServletContext().getAttribute("qstlist");
		getServletContext().removeAttribute("qstlist");
		
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
				
				int count = q2.getCorrectAnsweList().size();
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
				
				String[] checked = request.getParameterValues(i+"");				
				for(int j=0; j<checked.length; j++) {
					s.add(checked[j]);
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
		int time = 0;
		
		SaveData(uid, qid, score, time);
		response.sendRedirect("quizFinished.jsp?score="+score);
	}
	
	private void SaveData(int userid, int quizid, int score, int time) {
		UserManager um = (UserManager) getServletContext().getAttribute("userM");
		QuizDao qd = um.getQuizDao();
		History h = new History();
		
		h.setQuiz_id(quizid);
		h.setUser_id(userid);
		h.setScore(score);
		h.setTime(time);
		qd.addUserHostory(h);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
