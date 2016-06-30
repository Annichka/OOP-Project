package quiz.bean;

import java.util.Arrays;
import java.util.List;

/*
 * Am tipis kitxvis gaketebisas, shekitxvashi unda shaiweros '_'-ebi, da 
 * Answer-shi pasuxebi gamoikos ';'-ti.
 * */
public class FillInTheBlank extends Question {

	public FillInTheBlank(String question, String type, int questionid, int quizid, String c_answer, int answer_count) {
		super(question, type, questionid, quizid,  c_answer,answer_count );
	}
	
	public FillInTheBlank() {
		super();
	}
	
	public List<String> getCorrectAnsweList() {
		String[] data = this.c_answer.split(";");
		List<String> listed = Arrays.asList(data);
		return listed;
	}
}
