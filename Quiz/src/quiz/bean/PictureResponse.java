package quiz.bean;

public class PictureResponse extends Question {
	private String url;

	public PictureResponse(String question, String type, int questionid, int quizid, String url, String c_answer, int answer_count) {
		super(question, type, questionid, quizid,  c_answer, answer_count);
		this.url = url;
	}
	
	public PictureResponse(String question, String type, int questionid, int quizid, String c_answer, int answer_count) {
		super(question, type, questionid, quizid,  c_answer,  answer_count);
	}
	
	public PictureResponse() {
		super();
	}

	public void setPicUrl(String url) {
		this.url = url;
	}
	
	public String getPicUrl(){
		return this.url;
	}
}
