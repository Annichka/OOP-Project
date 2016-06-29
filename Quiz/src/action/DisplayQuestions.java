package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.Question;
import quiz.dao.QuestionDao;
import quiz.bean.PictureResponse;
import quiz.bean.MultiAnswer;
import quiz.bean.MultipleChoice;
import quiz.bean.Matching;

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
		String q = (String) getServletContext().getAttribute("quizprocess");
		Integer quizid = Integer.parseInt(q);
		ArrayList<Question> qList = null;
		
		try {
			qList = qDao.getQuestionsByQuizId(quizid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String jsp  = "";
		
		for (int i=0; i<qList.size(); i++) {
		 	Question curr = qList.get(i); 
			String qType = curr.getType(); 
			
			if(qType.equals("QR") ||  qType.equals("FB")) 
			{ 
				jsp += General(curr);
			}
			else if(qType.equals("MC"))
			{ 
				MultipleChoice elem = (quiz.bean.MultipleChoice) qList.get(i);
				jsp += MultipleChoice(elem);
			} 
			else if(qType.equals("MCA")) 
			{
				curr = (MultipleChoice) curr;
				jsp += MultipleChoiceAnswer((MultipleChoice)curr);	
			}
			else if(qType.equals("MA") ) 
			{
				curr = (MultiAnswer) curr;
				jsp += MultiAnswer((MultiAnswer)curr);
			} 
			else if(qType.equals("PR") ) 
			{
				curr = (PictureResponse) curr;
				jsp += PictureResponse((PictureResponse)curr);
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
						"<input type=\"hidden\" name=\"correctC\" value=\"1\" />"+
						"<input type=\"hidden\" name=\"wrongC\" value=\"0\" />"+
						"<input type=\"hidden\" name=\"questid\" value=\"" + curr.getQuestionId() + "\" />"+
						"<button> Edit <button>" +
					"</form> " +
			    "</div> <br>";
		return html;
	}
	
	private String PictureResponse(PictureResponse curr)
	{
		String html = 
				"<div class=\"form\">" +
					"<form action=\"EditQuestion\" method=\"post\">"+ 
						"<i>Question</i>"
						+ "<input type=\"text\" value=\"" + curr.getQuestion() + "\" name=\"quest\" /><br>" +
						"<i>Picture: </i>"
						+ "<input type=\"text\" value=\"" + curr.getPicUrl() + "\" name=\"picurl\" /><br>" +
						"<i>Answer:</i>"
						+ "<input type=\"text\" value=\"" + curr.getCAnswer() + "\" name=\"cansw\" /><br>"+
						"<input type=\"hidden\" name=\"correctC\" value=\"1\" />"+
						"<input type=\"hidden\" name=\"wrongC\" value=\"0\" />"+
						"<button> Submit <button>" +
					"</form> " +
			    "</div> <br>";
		return html;
	}
	
	private String MultiAnswer(MultiAnswer curr) 
	{
		String html = 
				"<div class=\"form\">"  +
				"<form action=\"EditQuestion\" method=\"post\">"+ "<i>Question:  </i>" +
				"<input type=\"text\" value=\"" + curr.getQuestion() + "\" name=\"quest\" /><br>"+
				"<input type=\"hidden\" name=\"type\" value=\"MA\" />"+
				"<input type=\"hidden\" name=\"ordered\" value=\"" + curr.getIsOrderd() + "\" />"+
				GenerateMultipleCorrect(curr.getAnswerCount(), curr.getAnswerList()) + 
				"<button> Submit <button>" +
				"</form> " +
			    "</div> <br>";
		return html;
	}
	
	private String MultipleChoice(MultipleChoice curr) 
	{
		String html = 
				"<div class=\"form\">"  +
				"<form action=\"EditQuestion\" method=\"post\">"+ "<i>Question</i>" +
				"<input type=\"text\" value=\"" + curr.getQuestion() + "\" name=\"quest\" /><br>" +
				"<i>Correct answer:</i><input type=\"text\" "
				+ "value=\"" + curr.getCAnswer() + "\" name=\"cansw\" /><br>"+
				"<input type=\"hidden\" name=\"type\" value=\"MC\" />"+
				GenerateMultipleWrong(curr.countWrongAnswers(), curr.getWrongAnsweList()) + 
				"<button> Submit <button>" +
				"</form> " +
			    "</div> <br>";

		return html;
	}
	
	private String MultipleChoiceAnswer(MultipleChoice curr) 
	{
		String html = 
				"<div class=\"form\">"  +
				"<form action=\"EditQuestion\" method=\"post\">"+ "<i>Question</i>" +
				"<input type=\"text\" value=\"" + curr.getQuestion() + "\" name=\"quest\" /><br>" +
				"<input type=\"hidden\" name=\"type\" value=\"MCA\" />"+
				GenerateMultipleCorrectWrong(curr.countCorrectAnswers(), curr.countWrongAnswers(), 
						curr.getCorrectAnsweList(), curr.getWrongAnsweList()) + 
				"<button> Submit <button>"+
				"</form> " +
			    "</div> <br>";

		return html;
	}
	
	
	private String GenerateMultipleCorrectWrong(int correctC, int wrongC, List<String> correct, List<String> wrong){
		String content = "";
		for (int i=0; i<correctC; i++) 
		{
			content += 
					"<i>Correct answer " + (i+1) + ": </i>" +
					"<input type\"text\" value=\"" + correct.get(i) + "\" name=\"cansw" + (i+1) + "\" "
					+ "id=\"cansw" + (i+1) + "\" /><br>";
		}
		
		for (int i=0; i<wrongC; i++) 
		{
			content += 
					"<i>Wrong answer " + (i+1) + ": </i>" +
					"<input type\"text\" value=\"" + wrong.get(i) + "\" name=\"wansw" + (i+1) + "\" "
					+ "id=\"wansw" + (i+1) + "\" /><br>";
		}
		
		content += "<input type=\"hidden\" name=\"correctC\" value=\"" + correctC + "\" />";
		content += "<input type=\"hidden\" name=\"wrongC\" value=\"" + wrongC + "\" />";
		return content;
	}
	
	
	private String GenerateMultipleCorrect(int count, List<String> answerList){
		String content = "";
		for (int i=0; i<count; i++) {
			content += 
					"<i>Correct answer " + (i+1) + ": </i>" +
					"<input type\"text\" value=\"" + answerList.get(i) + "\" name=\"cansw" + (i+1) + "\" "
					+ "id=\"cansw" + (i+1) + "\" /><br>";
		}
		
		content += "<input type=\"hidden\" name=\"correctC\" value=\"" + count + "\" />";
		content += "<input type=\"hidden\" name=\"wrongC\" value=\"" + 0 + "\" />";
		return content;
	}
	
	private String GenerateMultipleWrong(int count, List<String> wrong){
		String content = "";
		for (int i=0; i<count; i++) {
			content += 
					"<i>Wrong answer " + (i+1) + ": </i>" +
					"<input type\"text\" value=\"" + wrong.get(i) + "\" name=\"wansw" + (i+1) + "\" "
					+ "id=\"wansw" + (i+1) + "\" /><br>";
		}
		
		content += "<input type=\"hidden\" name=\"correctC\" value=\"" + 1 + "\" />";
		content += "<input type=\"hidden\" name=\"wrongC\" value=\"" +count + "\" />";
		return content;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
