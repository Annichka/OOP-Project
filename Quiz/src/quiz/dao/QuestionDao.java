package quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import quiz.bean.*;

public class QuestionDao {
	private final Connection conn;

	public QuestionDao(Connection conn) {
		this.conn = conn;
	}

	public void addQuestion(Question q) throws SQLException {
		Statement stmt = (Statement) conn.createStatement();

		if (q.getType().equals("MultipleChoice")||q.getType().equals("MultiAnswer")) {
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
	
	
	public void updateQuestio(Question q) {
		String sql = "";
		if (q.getType().equals("QR") || q.getType().equals("FB")) {
			sql = "UPDATE Questions SET question='" + q.getQuestion() + "', "
				+ "c_answer='" + q.getCAnswer() +"' WHERE id=" + q.getQuestionId() + ";";
		} else if(q.getType().equals("PR")) {
			sql = "UPDATE Questions SET question='" + q.getQuestion() + "', "
					+ "c_answer='" + q.getCAnswer() + "', pic_url='" + ((PictureResponse) q).getPicUrl()
					+"' WHERE id=" + q.getQuestionId() + ";";
		} else if(q.getType().equals("MC") || q.getType().equals("MCA") ) {
			sql = "UPDATE Questions SET question=" + q.getQuestion() + " "
					+ "c_answer=" + q.getCAnswer() + " w_answer= " + ((MultipleChoice) q).getWAnswers()
					+ " answer_count=" + ((MultipleChoice) q).getAnswerCount()
					+" WHERE id=" + q.getQuestionId() + ";";
		} else if(q.getType().equals("MA")) {
			sql = "UPDATE Questions SET question=" + q.getQuestion() + " "
					+ "c_answer=" + q.getCAnswer() + " ordered=" +  ((MultiAnswer) q).getIsOrderd()
					+ " answer_count=" + ((MultiAnswer) q).getAnswerCount()
					+" WHERE id=" + q.getQuestionId() + ";";
		}
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void deleteQuestionById(int id) throws SQLException {
		try (PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM Questions WHERE id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
	
	public Question getQuestionById(int id) {
		Question q = null;
		try (Statement stmt = conn.createStatement()) {
			try(ResultSet rslt = stmt.executeQuery("SELECT * FROM Questions WHERE id = " + id + ";")) {
				if(rslt.next()) {
					String type = rslt.getString("type");
					if(type.equals("QR") || type.equals("FB")) {
						q = new QuestionResponse();
					} else if(type.equals("PR")) {
						q = new PictureResponse();
						((PictureResponse) q).setPicUrl(rslt.getString("pic_url"));
					} else if(type.equals("MC") || type.equals("MCA") ) {
						q = new MultipleChoice();
						((MultipleChoice) q).setWAnswers(rslt.getString("w_answer"));
						((MultipleChoice) q).setOrdered(rslt.getInt("ordered"));
					} else if(type.equals("MA")) {
						q = new MultiAnswer();
						((MultiAnswer) q).setIsOrdered(rslt.getInt("ordered"));
					} else if(type.equals("M")) {
						// es ar vici jer rogor shevinaxo
					}
					q.setQuestionId(id);
					q.setQuestion(rslt.getString("question"));
					q.setCAnswer(rslt.getString("c_answer"));
					q.setAnswerCount(rslt.getInt("answer_count"));
					q.setType(type);
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return q;

	}

	public void deleteQuestionByQuizId(int id) throws SQLException {
		try (PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM Questions WHERE quiz_id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
	
	public ArrayList<Question> getQuestionsByQuizId(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Questions WHERE quiz_id = ?")) {
			stmt.setInt(1, id);
			try (ResultSet rslt = stmt.executeQuery()) {
				ArrayList<Question> question_list = new ArrayList<Question>();
				while (rslt.next()) {
					Question curr_quest = null;
					String type = rslt.getString("type");
					
					if (type.equalsIgnoreCase("PR")) {
						curr_quest = new PictureResponse();
						((PictureResponse) curr_quest).setPicUrl(rslt.getString("pic_url"));
					} else if (type.equalsIgnoreCase("MA")) {
						((MultiAnswer) curr_quest).setIsOrdered(rslt.getInt("ordered"));
					} else if (type.equalsIgnoreCase("MC")
							|| type.equalsIgnoreCase("MCA")) {
						curr_quest = new MultipleChoice();
						((MultipleChoice) curr_quest).setWAnswers(rslt.getString("w_answer"));
					}
					
					curr_quest = new QuestionResponse();
					question_list.add(curr_quest);
					
					curr_quest.setQuestionId(rslt.getInt("id"));
					curr_quest.setQuestion(rslt.getString("question"));
					curr_quest.setCAnswer(rslt.getString("c_answer"));
					curr_quest.setAnswerCount(rslt.getInt("answer_count"));
					curr_quest.setQuizId(rslt.getInt("quiz_id"));
					curr_quest.setType(rslt.getString("type"));
				}
				return question_list;
			}
		}
	}

}
