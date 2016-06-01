package quiz.bean;

public class PictureResponse extends Question {
	private String url;

	public PictureResponse(String question, int type, int questionid, int quizid, String url) {
		super(question, type, questionid, quizid);
		this.url = url;
	}
	
	public PictureResponse(String question, int type, int questionid, int quizid) {
		super(question, type, questionid, quizid);
	}
	
	public PictureResponse() {
		super();
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
}
