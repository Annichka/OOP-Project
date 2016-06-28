package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class DisplayQuestions
 */
@WebServlet("/DisplayQuestions")
public class DisplayQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayQuestions() {
        super();
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager uM = (UserManager) getServletContext().getAttribute("userM");
		QuestionDao qDao = uM.getQuestionDao();
		int quizid = (Integer) getServletContext().getAttribute("quizprocess");
		ArrayList<Question> qList = null;
		try {
			qList = qDao.getQuestionsByQuizId(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsp  = "";
		
		System.out.println(" LENGHT    " + qList.size());
		for (int i=0; i<qList.size(); i++) {
		 	Question curr = qList.get(i); 
			String qType = curr.getType(); 
			if(qType.equals("QR")) { 
				jsp += General(curr);
			}
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsp);
	}
	
	private String General(Question curr){
		String html = 
				"<div class=\"form\">" +
					"<form action=\"EditQuestion\" method=\"post\">"+
						"<i>Question:  </i>"
						+ "<input type=\"text\" value=\""+ curr.getQuestion() +
						"\" name=\"quest\" /><br>" +
						"<i>Answer</i>"
						+ "<input type=\"text\" value=\"" + curr.getCAnswer() +"\" name=\"cansw\" /><br>"+
						"<input type=\"hidden\" name=\"answcount\" value=\"1\" />"+
						"<input type=\"hidden\" name=\"questid\" value=\"" + curr.getQuestionId() + "\" />"+
						"<button> Edit <button>" +
					"</form> " +
			    "</div> <br>";
		return html;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
