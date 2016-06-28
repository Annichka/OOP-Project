package quiz.bean;

public class MultipleChoice extends Question {


	public MultipleChoice(String question, String type, int questionid,
			int quizid, String c_answer, int answer_count,  String wronganswers, int ordered) {
		super(question, type, questionid, quizid, c_answer, answer_count);
		this.w_answers = wronganswers;
		this.ordered = ordered;
	}

	public MultipleChoice(String question,String type, int questionid,
			int quizid, String c_answer, int answer_count) {
		super(question, type, questionid, quizid, c_answer, answer_count);
	}

	public MultipleChoice() {
		super();
	}

	public void setWAnswers(String wronganswers) {
		this.w_answers = wronganswers;
	}

	public String getWAnswers() {
		return this.w_answers;
	}
	
	public void setOrdered(int ord) {
		this.ordered = ord;
	}
	
	public int getOrdered() {
		return this.ordered;
	}
}
