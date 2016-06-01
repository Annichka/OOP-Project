package quiz.bean;

import java.util.Vector;

public class RandomQuiz extends Quiz {

	public RandomQuiz(String quizname, int quizid, int authorid, Vector<Question> questions) {
		super(quizname, quizid, authorid, questions);
	}
	
	public RandomQuiz() {
		super();
	}

	private void Shuffle() {
		// TODO: shuffle given vector of questions
	}

	public Vector<Question> GetQuestions() {
		this.Shuffle();
		return this.questions;
	}
}
