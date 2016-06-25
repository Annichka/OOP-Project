package quiz.bean;

public class Question {
	public Question(String question, String type, int questionid, int quizid, String c_answer, int answer_count) {
		this.question = question;
		this.questionid = questionid;
		this.type = type;
		this.quizid = quizid;
		this.answer_count= answer_count;
		this.c_answer = c_answer;
	}
	
	public Question() {
		super();
	}

	protected int questionid;
	protected String question;
	protected String c_answer;

	protected String type;
	protected int quizid;
	protected int answer_count;
	protected String picurl;
	protected String w_answer;
	protected int ordered;
	
	public void setQuestionId(int id) {
		this.questionid = id;
	} 
	
	public int getQuestionId() {
		return this.questionid;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String qst) {
		this.question = qst;
	}

	public String getCAnswer() {
		return this.c_answer;
	}

	public void setCAnswer(String ans) {
		this.c_answer = ans;
	}

	public void setType(String tp) {
		this.type = tp;
	}
	
	public String getType() {
		return this.type;
	}

	public void setQuizId(int id) {
		this.quizid = id;
	}
	
	public int getQuizId() {
		return this.quizid;
	}
	
	public void setAnswerCount(int c) {
		this.answer_count = c;
	}
	
	public int getAnswerCount() {
		return this.answer_count;
	}
}
