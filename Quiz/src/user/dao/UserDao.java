package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import user.bean.*;

public class UserDao {

	private final Connection conn;
	
	public UserDao(Connection conn) {
		this.conn = conn;
	}
	
	public void addUser(User usr) throws SQLException {
		Statement stmt = (Statement) conn.createStatement();
		String sql = "INSERT INTO Users (username, pass, e_mail, pic_url) " + "VALUES('" + usr.getUserName()
		+ "', '" + usr.getHashedPassword()+ "', '" + usr.getEMail() + "', '" + usr.getUserpic() +"')";
		stmt.executeUpdate(sql);
		addUserPriority(getUserByName(usr.getUserName()).getUserId(), 0);
	}

	public User getUserByName(String username) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
			stmt.setString(1, username);
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()) {
					User usr = new User();
					usr.setUserId(rslt.getInt("user_id"));
					usr.setUserName(rslt.getString("username"));
					usr.setHashedPassword(rslt.getString("pass"));
					usr.setEMail(rslt.getString("e_mail"));
					usr.setPicURL(rslt.getString("pic_url"));
					usr.setPriority(getUserPriority(usr.getUserId()));
					return usr;
				}
				return null;
			}
		}
	}
	
	public List<User> allUsers() throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users")) {
			try (ResultSet rslt = stmt.executeQuery()) {
				List<User> lst = new ArrayList<User>();
				while (rslt.next()) {
					User usr = new User();
					usr.setUserId(rslt.getInt("user_id"));
					usr.setUserName(rslt.getString("username"));
					usr.setHashedPassword(rslt.getString("pass"));
					usr.setEMail(rslt.getString("e_mail"));
					usr.setPicURL(rslt.getString("pic_url"));
					usr.setPriority(getUserPriority(usr.getUserId()));
					lst.add(usr);
				}
				return lst;
			}
		}
	}
	
	public ArrayList<User> allUserExcept(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users Where user_id != " + id)) {
			try (ResultSet rslt = stmt.executeQuery()) {
				ArrayList<User> lst = new ArrayList<User>();
				while (rslt.next()) {
					User usr = new User();
					usr.setUserId(rslt.getInt("user_id"));
	 				usr.setUserName(rslt.getString("username"));
					usr.setHashedPassword(rslt.getString("pass"));
					usr.setEMail(rslt.getString("e_mail"));
					usr.setPicURL(rslt.getString("pic_url"));
					usr.setPriority(getUserPriority(usr.getUserId()));
					lst.add(usr);
				}
				return lst;
			}
		}
	}
	public User getUserById(int id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE user_id = ?")) {
			stmt.setInt(1, id);
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()) {
					User usr = new User();
					usr.setUserId(rslt.getInt("user_id"));
					usr.setUserName(rslt.getString("username"));
					usr.setHashedPassword(rslt.getString("pass"));
					usr.setEMail(rslt.getString("e_mail"));
					usr.setPicURL(rslt.getString("pic_url"));
					usr.setPriority(getUserPriority(usr.getUserId()));
					return usr;
				}
				return null;
			}
		}
	}

	public String getHashedPassword(String user) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
			stmt.setString(1, user);
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()){
					return rslt.getString("pass");
				}
				return null;
			}
		}
	}
	
	public boolean accountExists(String username) throws SQLException {
		if(getUserByName(username) == null)
			return false;
		return true;
	}
	
	public void addUserPriority(int user, int priority) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO administrators(user_id, priority) VALUES(?, ?)")) {
			stmt.setInt(1, user);
			stmt.setInt(2, priority);
			stmt.executeUpdate();
		}
	}
	
	public void changePriority(int user, int priority) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE administrators SET priority = ? WHERE user_id = ?")) {
			stmt.setInt(1, priority);
			stmt.setInt(2, user);
			stmt.executeUpdate();
		}
	}
	
	public int getUserPriority(int user) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM administrators WHERE user_id = ?")) {
			stmt.setInt(1, user);
			try (ResultSet rslt = stmt.executeQuery()) {
				if (rslt.next()) {
					int priority = rslt.getInt("priority");
					return priority;
				}
				return 0;
			}
		}
	}
	
}