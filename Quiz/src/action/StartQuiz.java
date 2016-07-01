package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.FillInTheBlank;
import quiz.bean.Matching;
import quiz.bean.MultiAnswer;
import quiz.bean.MultipleChoice;
import quiz.bean.PictureResponse;
import quiz.bean.Question;
import quiz.bean.QuestionResponse;
import quiz.bean.Quiz;
import quiz.dao.QuestionDao;
import quiz.dao.QuizDao;

/**
 * Servlet implementation class StartQuiz
 */
@WebServlet("/StartQuiz")
public class StartQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int makeris = Integer.parseInt((String)getServletContext().getAttribute("id"));
		
		UserManager um = (UserManager) getServletContext().getAttribute("userM");
		QuestionDao qsd = um.getQuestionDao();
		QuizDao qd = um.getQuizDao();
		
		Integer qid = Integer.parseInt((String) request.getParameter("quizid"));
		
		Quiz quiz = null;
		ArrayList<Question> qstlist = null;
		String quizName = "";
		try {
			qstlist = qsd.getQuestionsByQuizId(qid);
			quizName = qd.getNameByQuizId(qid);
			quiz = qd.getQuizById(qid);
			
			if(quiz.isRandomized()) {
				Collections.shuffle(qstlist);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String html = "<h1> " +  quizName + "</h1>"
				+ "<form action=\"SubmitQuiz\" method=\"post\">" ;
		
		for (int i=0; i< qstlist.size(); i++) {
			html += QuestionForm(qstlist.get(i), i);
			html += "<br>";
		}
		
		html += "<br> <input type=\"hidden\" name=\"quizid\" id=\"quizid\" value=" + qid + "> "
				+ "  <button> Submit </button></form>";
		
		getServletContext().setAttribute("qstlist", qstlist);
		
		PrintWriter out = response.getWriter();
		out.write(html);
	}
	
	private String QuestionForm(Question q, int pos) {
		String html =  "";
		String type = q.getType();
		
		if(type.equals("QR")) {
			html += qrForm((QuestionResponse)q, pos);
		} else if (type.equals("FB")) {
			html += fbForm((FillInTheBlank) q, pos);
		} else if (type.equals("PR")) {
			html += prForm((PictureResponse)q, pos);
		} else if (type.equals("MC")) {
			html += mcForm((MultipleChoice)q, pos);
		} else if (type.equals("MCA")) {
			html += mcaForm((MultipleChoice)q, pos);
		} else if (type.equals("MA")) {
			html += maForm((MultiAnswer)q, pos);
		} else if (type.equals("M")) {
			html += mForm((Matching)q, pos);
		}
		return html;
	}
	
	private String qrForm(QuestionResponse q, int pos) {
		String html = "<br> <i>" + (pos+1) + ". " + q.getQuestion() + "</i> <br>" +
				"<textarea rows=\"1\" cols=\"15\" name=" + pos + "></textarea><br>"
				+ "";
		return html;
	}
	
	
	private String fbForm(FillInTheBlank q, int pos) {
		java.util.List<String> answers = q.getCorrectAnsweList();
		String html = "<br> <i>" + (pos+1) + ". " + q.getQuestion() + "</i> <br>";
		String name = pos + "x";
		for(int i=0; i<answers.size(); i++) {
			html +="<textarea rows=\"1\" cols=\"15\" name=" + name + i + "></textarea><br>"
				+ "";
		}
		return html;
	}
	
	
	private String prForm(PictureResponse q, int pos) {
		String html = "<br> <i>" + (pos+1) + ". " + q.getQuestion() + "</i>" +
				"<img src=\"" + q.getPicUrl() + "\" style=\"width:150px;height:150px;\"> </img>" +
				"<textarea rows=\"1\" cols=\"15\" name=" + pos + "></textarea><br>"
				+ "";
		return html;
	}
	

	private String mcForm(MultipleChoice q, int pos) {
		java.util.List<String> answers = q.getCorrectAnsweList();
		java.util.List<String> w_answers = q.getWrongAnsweList();
		
		java.util.List<String> all = new ArrayList<String>();
		all.addAll(answers);
		all.addAll(w_answers);
		
		Collections.shuffle(all);
		
		String html = "<br> <i>" + (pos+1) + ". " + q.getQuestion() + "</i> <br>";
		
		for(int i=0; i<all.size(); i++) {
			html +="<input type=\"radio\" name=\"" + pos + "\" value=\"" + all.get(i) + "\">" +  all.get(i) + "<br>"
				+ "";
		}
		return html;
	}
	
	
	private String mcaForm(MultipleChoice q, int pos) {
		java.util.List<String> canswers = q.getCorrectAnsweList();
		java.util.List<String> wanswers = q.getWrongAnsweList();
		
		java.util.List<String> all = new ArrayList<String>();
		all.addAll(canswers);
		all.addAll(wanswers);
		
		Collections.shuffle(all);
		
		String html = "<br> <i>" + (pos+1) + ". " + q.getQuestion() + "</i> <br>"
				+ "";
		String name = pos +"";
		for(int i=0; i<all.size(); i++) {
			html +="<input type=\"checkbox\" name=\"" + name + "\" value=\"" + all.get(i) + "\">" +  all.get(i) + "<br>"
				+ "";
		}
		html += "";
		return html;
	}
	
	
	private String maForm(MultiAnswer q, int pos) {
		java.util.List<String> answers = q.getAnswerList();
		String html = "<br> <i>" + (pos+1) + ". " + q.getQuestion() + "</i> <br>";
		String name = pos + "x";
		
		for(int i=0; i<answers.size(); i++) {
			html +="<textarea rows=\"1\" cols=\"15\" name=" + name + i + "></textarea>"
				+ "";
		}
		return html;
	}
	
	
	private String mForm(Matching q, int pos) {
		String html = "";
		ArrayList<String> fr = q.getFirstRow();
		ArrayList<String> sr = q.getSecondRow();
		int firstrow = fr.size();
		int secondrow = sr.size();
		String name = pos + "x";
		
		html += "<br><i>" + (pos+1) + ". " + q.getQuestion() + "</i>";
		
		for (int i=0; i< firstrow; i++) {
			html += "<p>" + fr.get(i) ;
			html += "<select name=\""+ name + i + "\">";
			for (int j=0; j< secondrow; j++) {
					html += "<option value=\"" + sr.get(j) +  "\">" + sr.get(j) + "</option>";	
			}
			html += "</select></p>";
			
		}
		return html;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
