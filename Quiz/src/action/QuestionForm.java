package action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.Question;
import quiz.dao.QuestionDao;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/QuestionForm")
public class QuestionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionForm() {
        super();
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserManager uM =(UserManager) getServletContext().getAttribute("userM");
		QuestionDao qDao = uM.getQuestionDao();		
		
		String type = (String) request.getParameter("type");
		int ansc =  Integer.parseInt((String) request.getParameter("cansc"));
		int wansc = Integer.parseInt((String) request.getParameter("wansc"));
		int ordered =  Integer.parseInt((String) request.getParameter("ord"));
				
		String question = "";
		
		if (type.equals("QR")) {
			question = General(type); 
		} 
		else if (type.equals("FB")) {
			question += FillBlank(type);
		} 
		else if(type.equals("MC")) {
			Integer wAnswCount = wansc;
			question = MultipleChoice(wAnswCount, type);
		} 
		else if(type.equals("PR")) {
			question += PictureResponse("PR");
		} 
		else if(type.equals("MA")){ 
			Integer cAnswCount = ansc;
			question += MultiAnswer(cAnswCount, type);
		} 
		else if(type.equals("MCA")){ 
			Integer cAnswCount = ansc;
			Integer wAnswCount = wansc;
			question += MultipleChoiceAnswer(cAnswCount, wAnswCount, type);
		} 
		else if(type.equals("M")) {
			System.out.println("TODO: MATCHING TYPE");
		}
        PrintWriter out = response.getWriter();
	    out.write(question);
	}
	
	
	private String General(String tp)
	{
		String html = 
				"<div class=\"form\">" +
					"<form action=\"AddQuestion\" method=\"post\">"+ 
						"<i>Question</i>"
						+ "<input type=\"text\" placeholder=\"Write question...\" name=\"quest\" /><br>" +
						"<i>Answer:</i>"
						+ "<input type=\"text\" placeholder=\"Write answer\" name=\"cansw\" /><br>"+
						"<input type=\"hidden\" name=\"correctC\" value=\"1\" />"+
						"<input type=\"hidden\" name=\"wrongC\" value=\"0\" />"+
						"<input type=\"hidden\" name=\"type\" value=\"" + tp + "\" />"+
						"<button> Submit <button>" +
					"</form> " +
			    "<div> <br>";
		return html;
	}
	
	private String FillBlank(String tp)
	{
		String html = 
				"<div class=\"form\">" +
					"<form action=\"AddQuestion\" method=\"post\">"+ 
						"<i>Question</i>"
						+ "<input type=\"text\" placeholder=\"Write question...\" name=\"quest\" /><br>" +
						"<i>Answer:</i>"
						+ "<input type=\"text\" placeholder=\"Write answer\" name=\"cansw\" /><br>"+
						"<input type=\"hidden\" name=\"correctC\" value=\"1\" />"+
						"<input type=\"hidden\" name=\"correctC\" value=\"0\" />"+
						"<input type=\"hidden\" name=\"type\" value=\"" + tp + "\" />"+
						"<button> Submit <button>" +
					"</form> " +
			    "<div> <br>";
		return html;
	}
	
	private String PictureResponse(String tp)
	{
		String html = 
				"<div class=\"form\">" +
					"<form action=\"AddQuestion\" method=\"post\">"+ 
						"<i>Question</i>"
						+ "<input type=\"text\" placeholder=\"Write question...\" name=\"quest\" /><br>" +
						"<i>Picture: </i>"
						+ "<input type=\"text\" placeholder=\"Write picture url...\" name=\"picurl\" /><br>" +
						"<i>Answer:</i>"
						+ "<input type=\"text\" placeholder=\"Write answer\" name=\"cansw\" /><br>"+
						"<input type=\"hidden\" name=\"correctC\" value=\"1\" />"+
						"<input type=\"hidden\" name=\"wrongC\" value=\"0\" />"+
						"<input type=\"hidden\" name=\"type\" value=\"" + tp + "\" />"+
						"<button> Submit <button>" +
					"</form> " +
			    "<div> <br>";
		return html;
	}
	
	private String MultiAnswer(int cAnswCount, String type) 
	{
		String html = 
				"<div class=\"form\">"  +
				"<form action=\"AddQuestion\" method=\"post\">"+ "<i>Question:  </i>" +
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>"+
				"<input type=\"hidden\" name=\"type\" value=\"" + type + "\" />"+
				GenerateMultipleCorrect(cAnswCount) + 
				"<button> Submit <button>";
		return html;
	}
	
	private String MultipleChoice(int wAnswCount, String type) 
	{
		String html = 
				"<div class=\"form\">"  +
				"<form action=\"AddQuestion\" method=\"post\">"+ "<i>Question</i>" +
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>" +
				"<i>Correct answer:</i><input type=\"text\" p"
				+ "laceholder=\"Correct answer..\" name=\"cansw\" /><br>"+
				"<input type=\"hidden\" name=\"type\" value=\"" + type + "\" />"+
				GenerateMultipleWrong(wAnswCount) + 
				"<button> Submit <button>";
		return html;
	}
	
	private String MultipleChoiceAnswer(int wAnswCount, int cAnswCount, String type) 
	{
		String html = 
				"<div class=\"form\">"  +
				"<form action=\"AddQuestion\" method=\"post\">"+ "<i>Question</i>" +
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>" +
				"<i>Correct answer:</i><input type=\"text\" p"
				+ "laceholder=\"Correct answer..\" name=\"cansw\" /><br>"+
				"<input type=\"hidden\" name=\"type\" value=\"" + type + "\" />"+
				GenerateMultipleCorrectWrong(cAnswCount, wAnswCount) + 
				"<button> Submit <button>";
		return html;
	}
	
	
	private String GenerateMultipleCorrectWrong(int correctC, int wrongC){
		String content = "";
		for (int i=0; i<correctC; i++) 
		{
			content += 
					"<i>Correct answer " + (i+1) + ": </i>" +
					"<input type\"text\" placeholder=\"Correct answer\" name=\"cansw" + (i+1) + "\" "
					+ "id=\"cansw" + (i+1) + "\" /><br>";
		}
		
		for (int i=0; i<wrongC; i++) 
		{
			content += 
					"<i>Wrong answer " + (i+1) + ": </i>" +
					"<input type\"text\" placeholder=\"Wrong answer\" name=\"wansw" + (i+1) + "\" "
					+ "id=\"wansw" + (i+1) + "\" /><br>";
		}
		
		content += "<input type=\"hidden\" name=\"correctC\" value=\"" + correctC + "\" />";
		content += "<input type=\"hidden\" name=\"wrongC\" value=\"" + wrongC + "\" />";
		return content;
	}
	
	
	private String GenerateMultipleCorrect(int count){
		String content = "";
		for (int i=0; i<count; i++) {
			content += 
					"<i>Correct answer " + (i+1) + ": </i>" +
					"<input type\"text\" placeholder=\"Correct answer\" name=\"cansw" + (i+1) + "\" "
					+ "id=\"cansw" + (i+1) + "\" /><br>";
		}
		
		content += "<input type=\"hidden\" name=\"correctC\" value=\"" + count + "\" />";
		content += "<input type=\"hidden\" name=\"wrongC\" value=\"" + 0 + "\" />";
		return content;
	}
	
	private String GenerateMultipleWrong(int count){
		String content = "";
		for (int i=0; i<count; i++) {
			content += 
					"<i>Wrong answer " + (i+1) + ": </i>" +
					"<input type\"text\" placeholder=\"Wrong answer\" name=\"wansw" + (i+1) + "\" "
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
