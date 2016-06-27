package action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String type = (String)request.getParameter("type");
		String ordered = "-1";
		String an = (String)request.getParameter("ansc");
		System.out.println(type + "    AIII AQANA    " + an);
		String question = "";
		if (type.equals("QR") || type.equals("FB")) {
			question += General("question");
		} else if(type.equals("MC")) {
			Integer wAnswCount = Integer.parseInt(an);
			question = MultipleChoice(wAnswCount);
		} else if(type.equals("PR")) {
			question += General("picture url");
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
		}
        PrintWriter out = response.getWriter();
	    out.write(question);
	}
	
	private String General(String qst){
		String html = "<div class=\"form\">" +
				"<form action=\"#\" method=\"post\">"+
				"<input type=\"text\" placeholder=\"Write "+ qst +"..\" name=\"quest\" /><br>" +
				"<input type=\"text\" placeholder=\"Correct answer..\" name=\"cansw\" /><br>"+
				"<button> Submit </button></form></div>";
		return html;
	}
	
	private String MultiAnswer(int cAnswCount) {
		String html = "<div class=\"form\">" +
				"<form action=\"#\" method=\"post\">"+
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>"+
				GenerateMultipleCorrect(cAnswCount)+
				"<button> Submit </button></form></div>";
		return html;
	}
	
	private String MultipleChoice(int wAnswCount) {
		String html = "<div class=\"form\">" +
				"<form action=\"#\" method=\"post\">"+
				"<input type=\"text\" placeholder=\"Write question..\" name=\"quest\" /><br>" +
				"<input type=\"text\" placeholder=\"Correct answer..\" name=\"cansw\" /><br>"+
				GenerateMultipleWrong(wAnswCount)+
				"<button> Submit </button></form></div>";
		
		return html;
	}
	
	private String GenerateMultipleCorrect(int count){
		String content = "";
		for (int i=0; i<count; i++) {
			content += "<input type\"text\" placeholder=\"Wrong answer\" name=\"cansw" + (i+1) + "\" /><br>";
		}
		return content;
	}
	
	private String GenerateMultipleWrong(int count){
		String content = "";
		for (int i=0; i<count; i++) {
			content += "<input type\"text\" placeholder=\"Wrong answer\" name=\"wansw" + (i+1) + "\" /><br>";
		}
		return content;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
