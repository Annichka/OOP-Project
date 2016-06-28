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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager uM =(UserManager) getServletContext().getAttribute("userM");
		QuestionDao qDao = uM.getQuestionDao();
		int questid = Integer.parseInt((String)request.getParameter("questid"));
		
		Question curr = qDao.getQuestionById(questid);
		
		String qstDispl = "";
		if (curr.getType().equals("QR")) {
			qstDispl = General(curr); //getTypeJsp(request, curr, "questionResponseT.jsp");
		} else if (curr.getType().equals("FB")) {
			qstDispl = getTypeJsp(request, curr, "fillBlankT.jsp");
		}		
		
		/*if (type.equals("QR")) {
			response.sendRedirect("questionResponseT.jsp");
		} else if (type.equals("FB")) {
			response.sendRedirect("fillBlankT.jsp");
			question += General("question", "FB");
		} else if(type.equals("MC")) {
			Integer wAnswCount = Integer.parseInt(an);
			question = MultipleChoice(wAnswCount);
		} else if(type.equals("PR")) {
			question += General("picture url", "PR");
		} else if(type.equals("MA")){ 
			Integer cAnswCount = Integer.parseInt(an);
			ordered = (String)request.getParameter("ordered");
			question += MultiAnswer(cAnswCount);
		} else if(type.equals("MCA")){ 
			Integer cAnswCount = Integer.parseInt(an);
			ordered = (String)request.getParameter("ordered");
			question += MultiAnswer(cAnswCount);
		} else if(type.equals("M")) {
			System.out.println("TODO: MATCHING TYPE");
		} */
        PrintWriter out = response.getWriter();
	    out.write(qstDispl);
	}
	
	
	private String getTypeJsp(HttpServletRequest request, Question qst, String jsp) {
		String path = (request.getSession()).getServletContext().getRealPath(jsp); 
		File file = new File(path);
		FileReader fileReader;
		String content = "";
		try {
			fileReader = new FileReader(file);
	        BufferedReader buffReader = new BufferedReader(fileReader);
	        String buffer = new String();
	        while( (buffer = buffReader.readLine() ) != null)
	            content += buffer;
	        buffReader.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	private String General(Question curr){
		String html = 
				"<div class=\"form\">" +
					"<form action=\"EditQuestion\" method=\"post\">"+ 
						"<i>Question</i>"
						+ "<input type=\"text\" value=\""+ curr.getQuestion() +
						"\" name=\"quest\" /><br>" +
						"<i>Answer</i>"
						+ "<input type=\"text\" value=\"" + curr.getCAnswer() +"\" name=\"cansw\" /><br>"+
						"<input type=\"hidden\" name=\"answcount\" value=\"1\" /><br>"+
						"<input type=\"hidden\" name=\"questid\" value=\"" + curr.getQuestionId() + "\" /><br>"+
						"<button> Edit <button>" +
					"</form> " +
			    "<div> <br>";
		return html;
	}
	
	private String MultiAnswer(int cAnswCount) {
		String html = "<div class=\"form\">"  +
				"<form action=\"#\" method=\"post\">"+ "<small>Question</small>" +
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>"+
				GenerateMultipleCorrect(cAnswCount) + 
				"<input type=\"submit\" value=\"Submit\" onClick=\"test(this)\"><br>";
		return html;
	}
	
	private String MultipleChoice(int wAnswCount) {
		String html = "<div class=\"form\">"  +
				"<form action=\"#\" method=\"post\">"+ "<small>Question</small>" +
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>" +
				"<small>Correct answer</small><input type=\"text\" placeholder=\"Correct answer..\" name=\"cansw\" /><br>"+
				GenerateMultipleWrong(wAnswCount) + 
				"<input type=\"submit\" value=\"Submit\" onClick=\"test(this)\"><br>";
		return html;
	}
	
	private String GenerateMultipleCorrect(int count){
		String content = "";
		for (int i=0; i<count; i++) {
			content += "<small>Correct answer " + (i+1) + "</small>" +
					"<input type\"text\" placeholder=\"Correct answer\" name=\"cansw" + (i+1) + "\" "
					+ "id=\"cansw" + (i+1) + "\" /><br>";
		}
		content += "<input type=\"hidden\" name=\"counter\" value=\"" +count + "\" /><br>";
		return content;
	}
	
	private String GenerateMultipleWrong(int count){
		String content = "";
		for (int i=0; i<count; i++) {
			content += "<small>Wrong answer " + (i+1) + "</small>" +
					"<input type\"text\" placeholder=\"Wrong answer\" name=\"wansw" + (i+1) + "\" "
					+ "id=\"wansw" + (i+1) + "\" /><br>";
		}
		content += "<input type=\"hidden\" name=\"counter\" value=\"" +count + "\" /><br>";
		return content;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
