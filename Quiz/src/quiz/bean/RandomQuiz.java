package quiz.bean;

import java.util.Vector;

public class RandomQuiz extends Quiz {

	public RandomQuiz(String quizname, int quizid, int authorid, Vector<Question> questions) {
		super(quizname, quizid, authorid, questions);
	}
	
	public RandomQuiz() {
		super();
	}

	
		// TODO: shuffle given vector of questions
	private void Shuffle(Vector <Question> vec) {
		Vector <Question> v= vec;
		Vector<Integer> indexs= new Vector <Integer>();
			while (true){
				int x=0;
				int y = v.size()-1;	 
				int number= (int) (Math.random()*x)+y;
				if (!indexs.contains(number)) indexs.add(number);
				if (indexs.size()==v.size()) break;
			} 
		Vector<Question> temp =new Vector<Question>();
		for (int i = 0; i<indexs.size(); i++){
			temp.add(v.get(i));
		}
	   v=temp;
	}

	public Vector<Question> GetQuestions() {
		this.Shuffle(questions);
		return this.questions;
	}


}
