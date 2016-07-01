package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.Matching;
import quiz.bean.MultiAnswer;
import quiz.bean.MultipleChoice;
import quiz.bean.PictureResponse;
import quiz.bean.Question;
import quiz.dao.QuestionDao;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		 * Adds new question into DataBase.
		 * Redirects to Category Page.
		 * 
		 * */
		
		UserManager man = (UserManager) getServletContext().getAttribute("userM");
		QuestionDao qdao = man.getQuestionDao();
				
		Integer quizid = Integer.parseInt((String)request.getParameter("quizid"));
		
		String qtype = (String) request.getParameter("type");
		String question = (String) request.getParameter("quest");
		
		Question newquestion = new Question();
				
		if(qtype.equals("QR") || qtype.equals("FB")) 
		{
			String answer = (String) request.getParameter("cansw");
			int count = Integer.parseInt((String)request.getParameter("correctC"));
			newquestion.setQuestion(question);
			newquestion.setCAnswer(answer);
			newquestion.setAnswerCount(count);
			newquestion.setType(qtype);
		} 
		else if(qtype.equals("PR")) 
		{
			newquestion = new PictureResponse();
			String url = (String) request.getParameter("picurl");
			String answer = (String) request.getParameter("cansw");
			int count = Integer.parseInt((String)request.getParameter("correctC"));
			
			newquestion.setQuestion(question);
			newquestion.setCAnswer(answer);
			newquestion.setAnswerCount(count);
			newquestion.setType(qtype);
			((PictureResponse)newquestion).setPicUrl(url);
		} else if (qtype.equals("MC"))
		{
			newquestion = new MultipleChoice();
			String answer = (String) request.getParameter("cansw");
			int counter = Integer.parseInt((String) request.getParameter("wrongC"));
			String wrongs = "";
			for (int i=0; i< counter; i++) 
			{
				wrongs += (String) request.getParameter("wansw" + (i+1)) + ";";
			}
			
			newquestion.setQuestion(question);
			newquestion.setCAnswer(answer);
			newquestion.setAnswerCount(1);
			newquestion.setType(qtype);
			((MultipleChoice)newquestion).setWAnswers(wrongs);
		} else if (qtype.equals("MCA"))
		{
			newquestion = new MultipleChoice();
			int correct_c = Integer.parseInt((String) request.getParameter("correctC"));
			int wrong_c = Integer.parseInt((String) request.getParameter("wrongC"));
			String wrongs = "";
			for (int i=0; i< wrong_c; i++) 
			{
				wrongs += (String) request.getParameter("wansw" + (i+1)) + ";";
			}
			String corrects ="";
			for (int i=0; i< correct_c; i++) 
			{
				corrects += (String) request.getParameter("cansw" + (i+1)) + ";";
			}
			
			newquestion.setQuestion(question);
			newquestion.setCAnswer(corrects);
			newquestion.setType(qtype);
			((MultipleChoice)newquestion).setWAnswers(wrongs);
			newquestion.setAnswerCount(correct_c);
		} else if (qtype.equals("MA"))
		{
			newquestion = new MultiAnswer();
			int ordered = Integer.parseInt((String) request.getParameter("ordered"));
			int counter = Integer.parseInt((String) request.getParameter("correctC"));
		
			String corrects ="";
			for (int i=0; i< counter; i++) 
			{
				corrects += (String) request.getParameter("cansw" + (i+1)) + ";";
			}
			newquestion.setType(qtype);			
			newquestion.setQuestion(question);
			newquestion.setCAnswer(corrects);
			((MultiAnswer)newquestion).setIsOrdered(ordered);
			newquestion.setAnswerCount(counter);
		}
		else if (qtype.equals("M"))
		{
			newquestion = new Matching();
			int counter = Integer.parseInt((String) request.getParameter("correctC"));
		
			String corrects ="";
			for (int i=0; i< counter; i++) 
			{
				corrects += (String) request.getParameter("cansw" + (i+1)) + ";";
			}
			newquestion.setType(qtype);			
			newquestion.setQuestion(question);
			newquestion.setCAnswer(corrects);
			newquestion.setAnswerCount(counter);
		}
		
		newquestion.setQuizId(quizid);

		try 
		{
			qdao.addQuestion(newquestion);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().removeAttribute("questionshown");
		response.sendRedirect("startQuestionTypes.jsp?quizid=" + quizid);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
