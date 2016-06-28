package quiz.bean;

public class MultiAnswer extends Question {
	
	public MultiAnswer(String question, String type, int questionid, int quizid, String c_answer, int answer_count, 
			int ordered) {
		super(question, type, questionid, quizid, c_answer, answer_count);	
		this.ordered = ordered;
	}
	
	public MultiAnswer() {
		super();
	}
	
	public void setIsOrdered(int ordered) {
		this.ordered = ordered;
	}
	
	public int getIsOrderd() {
		return this.ordered;
	}
}
