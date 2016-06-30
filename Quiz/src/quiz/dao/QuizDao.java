package quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public String getNameByQuizId(int id) throws SQLException {
		String name = "Quiz Not Found";
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE quiz_id = "
				+ id + ";")) {
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()) {
					name = rslt.getString("quiz_name");
				}
			}
		}
		return name;
	}
	
	/*
	 * Return quiz list by Author 
	 * */
	public ArrayList<Quiz> getQuizByCreator(int authorid) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE author_id = ?")) {
			stmt.setInt(1, authorid);
			try (ResultSet rslt = stmt.executeQuery()) {
				ArrayList<Quiz> users_quiz = new ArrayList<>();
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
	public ArrayList<Quiz> getQuizByCategory(String category) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes WHERE category = ?")) {
			stmt.setString(1, category);
			try (ResultSet rslt = stmt.executeQuery()) {
				ArrayList<Quiz> categorys_quiz= new ArrayList<>();
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

	public ArrayList<Quiz> getQuizList() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			try(ResultSet rslt = stmt.executeQuery("SELECT * FROM Quizes")) {
				ArrayList<Quiz> quiz_list = new ArrayList<>();
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
	
	public ArrayList<Quiz> getNewQuizes() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			try(ResultSet rslt = stmt.executeQuery("SELECT * FROM Quizes ORDER BY quiz_id DESC;")) {
				ArrayList<Quiz> quiz_list = new ArrayList<>();
				for (int i=0; i<5; i++) {
					if (rslt.next()){
						Quiz curr_quiz = new Quiz();
						curr_quiz.setQuizId(rslt.getInt("quiz_id"));
						curr_quiz.setQuizName(rslt.getString("quiz_name"));
						quiz_list.add(curr_quiz);
					}
				}
				return quiz_list;
			}
		}
	}
	

	public void setQuizFinished(int quizId) {
		String sql = "UPDATE Quizes SET finished=1 where quiz_id = " + quizId + ";";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int createNewQuiz(String quizName, int authorId, String category, int isRandom, int finished) {
		try {
			Statement stmt = (Statement) conn.createStatement();
			String sql = "INSERT INTO Quizes(quiz_name, author_id, category, isRandom, pages, correction, practice, finished)"
				+ "VALUES('" + quizName +"', "+ authorId + ", '" +category +"', "+ isRandom + ", "
						+ "1, 0, 0, " + finished + ")";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int lastid = -1;
		try (Statement stmt2 = conn.createStatement()) {
			try(ResultSet rslt = stmt2.executeQuery("SELECT * FROM Quizes WHERE author_id = " + authorId +" "
					+ "ORDER BY quiz_id DESC;")) {
				if(rslt.next()) {
					lastid = rslt.getInt("quiz_id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lastid;
	}
	
	public ArrayList<Quiz> getTopQuizes() {
		ArrayList<Quiz> top= new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Quizes ORDER BY filled DESC;")) {
			try (ResultSet rslt = stmt.executeQuery()) {
				for(int i=0; i<5; i++) {
					if (rslt.next()) {
						Quiz curr_quiz = new Quiz();
						curr_quiz.setQuizId(rslt.getInt("quiz_id"));
						curr_quiz.setQuizName(rslt.getString("quiz_name"));
						curr_quiz.setAuthorId(rslt.getInt("author_id"));
						top.add(curr_quiz);
					}
				}
				return top;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top;
	}
	
	public ArrayList<String> getCategories() {
		ArrayList<String> cat= new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Categories;")) {
			try (ResultSet rslt = stmt.executeQuery()) {
				for(int i=0; i<5; i++) {
					if (rslt.next()) {
						String c = rslt.getString("c_name");
						cat.add(c);
					}
				}
				return cat;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	public ArrayList<History> getUserHistory(int userid) {
		ArrayList<History> hist= new ArrayList<>();
		
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM History Where user_id = " 
				+ userid + ";")) {
			try (ResultSet rslt = stmt.executeQuery()) {
				while (rslt.next()) {
						History h = new History();
						h.setId(rslt.getInt("id"));
						h.setQuiz_id(rslt.getInt("quiz_id"));
						h.setUser_id(rslt.getInt("user_id"));
						h.setScore(rslt.getInt("score"));
						hist.add(h);
					}
				}
				return hist;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return hist;
	}
	
	
	public void addUserHostory(History h) {
		Statement stmt;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "INSERT INTO History(user_id, quiz_id, score, f_time)" 
					+ "VALUES("+ h.getUser_id() + ", " + h.getQuiz_id() +", "+ h.getScore() + ", " + h.getTime() + ")";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
