package quiz.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Scoring {
	
	public Scoring() {}
	
	public int countForQuiz(ArrayList<Question> qlist, ArrayList<ArrayList<String>> anslist) {
		int score = 0;
		for (int i =0; i< qlist.size(); i++) {
			int ord = 0;
			if(qlist.get(i).getType().equals("MA"))
				ord = ((MultiAnswer)qlist.get(i)).getIsOrderd();
			score += getScore(qlist.get(i).getCAnswer(), anslist.get(i), qlist.get(i).getType(), ord);
		}
		return score;
	}
	
	public int getScore(String c_answ, ArrayList<String> input, String qtype, int ordered) {
		int score = 0;		
		
		if(qtype.equals("QR")) {
			score += qrScoring(c_answ, input);
		}
		else if(qtype.equals("FB")) {
			score += fbScoring(c_answ, input);
		}
		else if(qtype.equals("PR")) {
			score += prScoring(c_answ, input);
		}
		else if(qtype.equals("MC")) {
			score += mcScoring(c_answ, input);
		}
		else if(qtype.equals("MCA")) {
			score += mcaScoring(c_answ, input);
		}
		else if(qtype.equals("MA")) {
			score += maScoring(c_answ, input, ordered);
		}
		
		return score;
	}
	
	
	private int qrScoring(String c_answ, ArrayList<String> input) {
		int score = 0;
			
		if(input.get(0) == null || input.get(0) == "")
			return score;
		if(input.get(0).equals(c_answ))
			score++;
		else {
			int c = 0;
			for (int i=0; i<input.size(); i++) {
				if (input.get(i) == null  || input.get(i) == "")
					continue;
				if(c_answ.contains(input.get(i)))
					c++;
			}
			if (c == input.size())
				score++;
		}
		return score;
	}

	private int fbScoring(String c_answ, ArrayList<String> input) {
		int score = 0;
		String[] corr = c_answ.split(";");
		for (int i=0; i < corr.length; i++) {
			if (input.get(i) == null  || input.get(i) == "")
				continue;
			if(corr[i].equals(input.get(i))) {
				score++;
			} else {
				score--;
			}
		}
		return score;
	}
	
	private int prScoring(String c_answ, ArrayList<String> input) {
		int score = 0;
		if(input.equals(c_answ))
			score++;
		else {
			int c = 0;
			for (int i=0; i<input.size(); i++) {
				if (input.get(i) == null || input.get(i) == "")
					continue;
				if(c_answ.contains(input.get(i)))
					c++;
			}
			if (c == input.size())
				score++;
		}
		return score;
	}

	private int mcScoring(String c_answ, ArrayList<String> input) {
		int score = 0;
		if (input.get(0) != null)
			if(c_answ.equals(input.get(0)))
				score++;
		return score;
	}
	
	private int mcaScoring(String c_answ, ArrayList<String> input) {
		int score = 0;
		String[] corr = c_answ.split(";");
		List<String> listed = Arrays.asList(corr);
		if(input.get(0) == null) {
			return score;
		}
		for (int i=0; i<input.size(); i++) {
			if (listed.contains(input.get(i))) 
				score++;
			else 
				score--;
		}
		return score;
	}
	
	private int maScoring(String c_answ, ArrayList<String> input, int ordered) {
		int score = 0;
		String[] corr = c_answ.split(";");
		List<String> listed = Arrays.asList(corr);
		
		if(ordered == 1) {
			for (int i=0; i<listed.size(); i++) {
				if(input.get(i) == null || input.get(i) == "")
					continue;
				if (listed.get(i).equals(input.get(i))) 
					score++;
				else 
					score--;
			}
		}
		else {
			for (int i=0; i<listed.size(); i++) {
				if(input.get(i) == "" || input.get(i) == null)
					continue;
				if (listed.contains(input.get(i))) 
					score++;
				else 
					score--;
			}
		}
		return score;
	}
}

