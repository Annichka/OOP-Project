package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.bean.*;

public class MessagesDao {
	
	private final Connection conn;
	
	public MessagesDao(Connection conn) {
		this.conn = conn;
	}

	public void addMessage(Messages sms) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Messages (u_from, u_to, message, m_type, quiz_id) VALUE("
				+ sms.getSender() +", " + sms.getReceiver() + ", '" + sms.getMessage() + "', '" 
				+ sms.getMType() + "', " + sms.getQuizId() + ")")) {
			stmt.executeUpdate();
		}
	}
	
	public void addFriendRequest(Messages sms) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Messages (u_from, u_to, message, m_type) VALUE("
				+ sms.getSender() +", " + sms.getReceiver() + ", '" + sms.getMessage() + "', '" 
				+ sms.getMType() + "')")) {
			stmt.executeUpdate();
		}
	}

	public void deleteMessageById(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Messages WHERE id = ?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
	/* ტიპით და სენდერ/რისივერით შლის მესიჯს. Note-ების წასაშლელი*/
	public void deleteMessage(Messages msg) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Messages WHERE u_from = ? && u_to = ? && message = ? && m_type = ?")) {
			stmt.setInt(1, msg.getSender());
			stmt.setInt(2, msg.getReceiver());
			stmt.setString(3, msg.getMessage());
			stmt.setString(4, msg.getMType());
			stmt.executeUpdate();
		}
	}
	/* ჩელენჯების წასაშლელი */
	public void deleteMessageByQuiz(Messages msg) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Messages WHERE u_from = ? && u_to = ? && m_type = ? && quiz_id = ? ")) {
			stmt.setInt(1, msg.getSender());
			stmt.setInt(2, msg.getReceiver());
			stmt.setString(3, msg.getMType());
			stmt.setInt(4, msg.getQuizId());
			stmt.executeUpdate();
		}
	}
	
	/* მიბრუნებს ჩემთან გამოგზავნილ მესიჯებს (და არა ჩემგან გაგზავნილებს!) [[წესით]]*/
	public List<Messages> getUserMessages(int usrId) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Messages WHERE u_to = ?")) {
			stmt.setInt(1, usrId);
			try (ResultSet rslt = stmt.executeQuery()) {
				List<Messages> user_messages = new ArrayList<>();
				while(rslt.next()) {
					Messages msg = new Messages();
					msg.setId(rslt.getInt("id"));
					msg.setSender(rslt.getInt("u_from"));
					msg.setReceiver(rslt.getInt("u_to"));
					msg.setMessage(rslt.getString("message"));
					msg.setMType(rslt.getString("m_type"));
					msg.setQuizId(rslt.getInt("quiz_id"));
					user_messages.add(msg);
				}
				return user_messages;
			}
		}
	}
	
	public List<Messages> getFriendRequests(int usrId) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Messages WHERE u_to = ? AND m_type = \"friendrequest\"")) {
			stmt.setInt(1, usrId);
			try (ResultSet rslt = stmt.executeQuery()) {
				List<Messages> user_messages = new ArrayList<>();
				while(rslt.next()) {
					Messages msg = new Messages();
					msg.setId(rslt.getInt("id"));
					msg.setSender(rslt.getInt("u_from"));
					msg.setReceiver(rslt.getInt("u_to"));
					msg.setMessage(rslt.getString("message"));
					msg.setMType(rslt.getString("m_type"));
					user_messages.add(msg);
				}
				return user_messages;
			}
		}
	}
}
