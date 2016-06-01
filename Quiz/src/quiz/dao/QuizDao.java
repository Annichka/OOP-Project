package quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import quiz.bean.*;

public class QuizDao {
	
	private final Connection conn;
    
	public QuizDao(Connection conn) {
		this.conn = conn;
	}
	
	/*
	 * Delete quiz by id
	 * */
	public void deleteQuiz(int quiz_id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Quizes WHERE quiz_id = " + quiz_id + "")) {
			stmt.executeUpdate();
		}
	}
	
	/*
	 * Return quiz by id
	 * */
	public Quiz getQuizById(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE quiz_id = ?")) {
			stmt.setInt(1, id);
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()) {
					Quiz curr_quiz = new Quiz();
					curr_quiz.setQuizId(rslt.getInt("quiz_id"));
					curr_quiz.setQuizName(rslt.getString("quiz_name"));
					curr_quiz.setAuthorId(rslt.getInt("author_id"));
					curr_quiz.setCategory(rslt.getString("category"));
					curr_quiz.setRandomized(rslt.getInt("isRandom"));
					curr_quiz.setCorrection(rslt.getInt("correction"));
					curr_quiz.setPages(rslt.getInt("pages"));
					curr_quiz.setPractice(rslt.getInt("practice"));
					return curr_quiz;
				}
				return null;
			}
		}
	}
	
	/*
	 * Return quiz id by quiz name
	 * */
	public int getQuizId(String quizName) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE quiz_name = ?")) {
			stmt.setString(1, quizName);
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()) {
					return rslt.getInt("quiz_id");
				}
				return -1;
			}
		}
	}
	
	/*
	 * Return quiz list by Author 
	 * */
	public List<Quiz> getQuizByCreator(int authorid) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE author_id = ?")) {
			stmt.setInt(1, authorid);
			try (ResultSet rslt = stmt.executeQuery()) {
				List<Quiz> users_quiz = new ArrayList<>();
				while (rslt.next()) {
					Quiz curr_quiz = new Quiz();
					curr_quiz.setQuizId(rslt.getInt("quiz_id"));
					curr_quiz.setQuizName(rslt.getString("quiz_name"));
					users_quiz.add(curr_quiz);
				}
				return users_quiz;
			}
		}
	} 
	
	
	/*
	 * Return quiz list by category 
	 * */
	public List<Quiz> getQuizByCategory(String category) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE category = ?")) {
			stmt.setString(1, category);
			try (ResultSet rslt = stmt.executeQuery()) {
				List<Quiz> categorys_quiz= new ArrayList<>();
				while (rslt.next()) {
					Quiz curr_quiz = new Quiz();
					curr_quiz.setQuizId(rslt.getInt("quiz_id"));
					curr_quiz.setQuizName(rslt.getString("quiz_name"));
					curr_quiz.setAuthorId(rslt.getInt("author_id"));
					categorys_quiz.add(curr_quiz);
				}
				return categorys_quiz;
			}
		}
	}

	public List<Quiz> getQuizList() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			try(ResultSet rslt = stmt.executeQuery("SELECT * FROM Quizes")) {
				List<Quiz> quiz_list = new ArrayList<>();
				while (rslt.next()) {
					Quiz curr_quiz = new Quiz();
					curr_quiz.setQuizId(rslt.getInt("quiz_id"));
					curr_quiz.setQuizName(rslt.getString("quiz_name"));
					quiz_list.add(curr_quiz);
				}
				return quiz_list;
			}
		}
	}
}
