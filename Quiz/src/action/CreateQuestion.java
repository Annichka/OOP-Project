package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import quiz.bean.Question;
import quiz.dao.QuestionDao;

/**
 * Servlet implementation class CreateQuestion
 */
@WebServlet("/CreateQuestion")
public class CreateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn = request.getParameter("next");
		System.out.println(btn);
		String type = (String)request.getParameter("type");
		Question q= new Question();
		setParameters(q, request);
		
		UserManager usrM;
		QuestionDao dao;
		try {
			usrM = (UserManager) getServletContext().getAttribute("userM");
			dao= usrM.getQuestionDao();
			dao.addQuestion(q);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String done = request.getParameter("done");
		String more = request.getParameter("more");
		if(done==null) {
			response.sendRedirect("createQuiz.jsp");
		} else if(more==null){
			response.sendRedirect("showFinishedQuiz.jsp");
		}
		
	}

	private void setParameters(Question q, HttpServletRequest request) {
		String questiont=(String)getServletContext().getAttribute("type");
		q.setType(questiont);
		q.setQuestion(request.getParameter("question"));
		q.setCAnswer(request.getParameter("answer"));
		q.setQuizId((int)getServletContext().getAttribute("QuizId"));
		String answers="";
		if(questiont.equals("04")){
			//q.setPicUrl(request.getParameter("question"));
		}
		if(questiont.equals("03") || questiont.equals("06")){
			
			String correctAnswers="";
			for(int i=0; i < 8; i++){
				String answer=request.getParameter("answer"+i);
				String check=request.getParameter("check"+i);
					if(answer!=null && !answer.isEmpty()) answers+=answer+"/";
				if(check!=null)correctAnswers+=answer+"/";
			}
			q.setCAnswer(answers);
			//q.setCorrectAnswer(correctAnswers);
		}
		if(questiont.equals("05")){
			
			if(request.getParameter("ordered")!=null)answers+="#";
			for(int i=0; i < 8; i++){
				String answer=request.getParameter("answer"+i);
				if(answer!=null && !answer.isEmpty()) answers+=answer+"/";
				
			}
			q.setCAnswer(answers);
			
		} 
		if(questiont.equals("07")){
			String questions="";
			for(int i=0; i < 8; i++){
				String answer=request.getParameter("answer"+i);
				if(answer!=null && !answer.isEmpty()){
					answers+=answer+"/";
				}
				String question=request.getParameter("question"+i);
				if(question!=null && !question.isEmpty())questions+=question+"/";
			}
			q.setCAnswer(answers);
			q.setQuestion(questions);
		}	
		
	}

}
