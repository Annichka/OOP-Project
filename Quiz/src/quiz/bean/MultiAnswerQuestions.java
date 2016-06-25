package quiz.bean;

public class MultiAnswerQuestions extends Question{

	public MultiAnswerQuestions(String question, String type, int questionid, int quizid,String c_answer, int answer_count, String w_answer, int ordered) {
		super(question, type, questionid, quizid, c_answer, answer_count);	

	}
	
	public MultiAnswerQuestions() {
		super();
	}
	public void setWAnswer(String w_answer) {
		this.w_answer = w_answer;
	}
	
	public String getWAnswer(){
		return this.w_answer;
	}
	public void setIsOrdered(int ordered) {
		this.ordered = ordered;
	}
	
	public int getIsOrderd() {
		return this.ordered;
	}
}
