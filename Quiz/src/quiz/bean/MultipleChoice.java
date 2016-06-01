package quiz.bean;

public class MultipleChoice extends Question {
	private String wronganswers;

	public MultipleChoice(String question, int type, int questionid, int quizid,String wronganswers) {
		super(question, type, questionid, quizid);
		this.wronganswers = wronganswers;
	}
	
	public MultipleChoice(String question, int type, int questionid, int quizid) {
		super(question, type, questionid, quizid);
	}
	
	public MultipleChoice() {
		super();
	}

	public void setWrongAnswers(String wronganswers) {
		this.wronganswers = wronganswers;
	}

	public String getWrongAnswers() {
		return this.wronganswers;
	}
}
