package quiz.bean;

public class QuestionResponse extends Question {
	
	public QuestionResponse(String question,String type,int questionid,int quizid, String c_answer, int answer_count) {
		super(question,type,questionid,quizid, c_answer,  answer_count);
	}
	public QuestionResponse() {
		super();
	}
}
