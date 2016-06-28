package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// unda chaagdos bazashi axali kitxva
		// gadaviyvano kategoriebis gverdze
		
		UserManager man = (UserManager) getServletContext().getAttribute("userM");
		QuestionDao qdao = man.getQuestionDao();
		
		String question = (String) request.getParameter("quest");
		String answer = (String) request.getParameter("cansw");
		int count = Integer.parseInt((String)request.getParameter("answc"));
		String qtype = (String) request.getParameter("type");
		
		Question newquestion = new Question();
		newquestion.setQuestion(question);
		newquestion.setCAnswer(answer);
		newquestion.setAnswerCount(count);
		newquestion.setType(qtype);
		
		if(qtype.equals("PR")) 
		{
			String url = (String) request.getParameter("picurl");
			((PictureResponse)newquestion).setPicUrl(url);
		} else if (qtype.equals("MC"))
		{
			int counter = Integer.parseInt((String) request.getParameter("counter"));
			for (int i=0; i< counter; i++) 
			{
				
			}
		}
		
		try 
		{
			qdao.addQuestion(newquestion);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("startQuestionTypes.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
