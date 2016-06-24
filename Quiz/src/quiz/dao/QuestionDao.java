package quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import quiz.bean.*;

public class QuestionDao {
	private final Connection conn;
	
	public QuestionDao(Connection conn) {
		this.conn = conn;
	}
	
	/*public void addQuestion(Question q) throws SQLException {
		Statement stmt = (Statement) conn.createStatement();
		
		String sql = "INSERT INTO Questions (question, quest_type, c_answer, w_answer, answer_count, pic_url, quiz_id) " + "VALUES('" + q.getQuestion()
		+ "', '" + q.getType() + "', '" + q.getCAnswer()+ "', '"+ q.getWAnswers() + "', '" + q.getAnswerCount() +
		"', '"+ q.getPicUrl() +"', '"+ q.getQuizId() +"')";
		stmt.executeUpdate(sql);
	}*/
	
	public void deleteQuestionById(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Questions WHERE id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
	
	public void deleteQuestionByQuizId(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Questions WHERE quiz_id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
	/*
	public ArrayList <Question> getQuestionsByQuizId(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Questions WHERE quiz_id = ?")) {
			stmt.setInt(1, id);
			try(ResultSet rslt = stmt.executeQuery()) {
				ArrayList <Question> question_list = new ArrayList<Question>();
				while (rslt.next()) {
					Question curr_quest = new Question();
					curr_quest.setQuestionId(rslt.getInt("id"));
					curr_quest.setQuestion(rslt.getString("question"));
					curr_quest.setCAnswer(rslt.getString("c_answer"));
					curr_quest.setWAnswers(rslt.getString("w_answer"));
					curr_quest.setAnswerCount(rslt.getInt("answer_count"));
					curr_quest.setPicUrl(rslt.getString("pic_url"));
					curr_quest.setQuizId(rslt.getInt("quiz_id"));
					curr_quest.setType(rslt.getInt("quest_type"));
					question_list.add(curr_quest);
				}
				return question_list;
			}
		}
	}	*/
}
