package quiz.bean;

public class History {
	
	private int id;
	private int user_id;
	private int quiz_id;
	private int score;
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the quiz_id
	 */
	public int getQuiz_id() {
		return quiz_id;
	}
	/**
	 * @param quiz_id the quiz_id to set
	 */
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
