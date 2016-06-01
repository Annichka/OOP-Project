package quiz.bean;

public class Question {
	public Question(String question, int type, int questionid, int quizid) {
		this.question = question;
		this.questionid = questionid;
		this.type = type;
		this.quizid = quizid;
	}
	
	public Question() {
		super();
	}

	protected int questionid;
	protected String question;
	protected String c_answer;
	protected String w_answer;
	protected int type;
	protected int quizid;
	protected int answer_count;
	protected String picurl;
	
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

	public String getWAnswers() {
		return this.w_answer;
	}

	public void setWAnswers(String ans) {
		this.w_answer = ans;
	}
	
	public void setType(int tp) {
		this.type = tp;
	}
	
	public int getType() {
		return this.type;
	}

	public void setQuizId(int id) {
		this.quizid = id;
	}
	
	public int getQuizId() {
		return this.quizid;
	}

	public int getQuestionID() {
		return this.questionid;
	}
	
	public void setAnswerCount(int c) {
		this.answer_count = c;
	}
	
	public int getAnswerCount() {
		return this.answer_count;
	}
	
	public void setPicUrl(String url) {
		this.picurl = url;
	}
	
	public String getPicUrl() {
		return this.picurl;
	}
	

}
