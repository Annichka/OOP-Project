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

	public void addQuestion(Question q) throws SQLException {
		Statement stmt = (Statement) conn.createStatement();

		if (q.getType().equals("MultipleChoice")||q.getType().equals("MultiAnswerQuestions")) {
			String sql = "INSERT INTO Questions (question, quest_type, c_answer, w_answer, answer_count, quiz_id) "
					+ "VALUES('"
					+ q.getQuestion()
					+ "', '"
					+ q.getType()
					+ "', '"
					+ q.getCAnswer()
					+ "', '"
					+ ((MultipleChoice) q).getWAnswers()
					+ "', '"
					+ q.getAnswerCount() + "', '" + q.getQuizId() + "')";
			stmt.executeUpdate(sql);
		}
		if (q.getType().equals("PictureResponse")) {
			String sql = "INSERT INTO Questions (question, quest_type, c_answer, answer_count, pic_url, quiz_id) "
					+ "VALUES('"
					+ q.getQuestion()
					+ "', '"
					+ q.getType()
					+ "', '"
					+ q.getCAnswer()
					+ "', '"
					+ q.getAnswerCount()
					+ "', '"
					+ ((PictureResponse) q).getPicUrl()
					+ "', '"
					+ q.getQuizId() + "')";
			stmt.executeUpdate(sql);
		}
		if (!q.getType().equals("PictureResponse")
				&& !q.getType().equals("MultipleChoice")) {
			String sql = "INSERT INTO Questions (question, quest_type, c_answer, answer_count, quiz_id) "
					+ "VALUES('"
					+ q.getQuestion()
					+ "', '"
					+ q.getType()
					+ "', '"
					+ q.getCAnswer()
					+ "', '"
					+ q.getAnswerCount()
					+ "', '" + q.getQuizId() + "')";
			stmt.executeUpdate(sql);
		}
	}

	public void deleteQuestionById(int id) throws SQLException {
		try (PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM Questions WHERE id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	public void deleteQuestionByQuizId(int id) throws SQLException {
		try (PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM Questions WHERE quiz_id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

}
