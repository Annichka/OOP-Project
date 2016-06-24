
package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.bean.Friends;
import user.bean.User;

public class FriendsDao {
	
	private final Connection conn;
	
	public FriendsDao(Connection conn) {
		this.conn = conn;
	}
	/* ვეუბნები User-ის სახელს და მიბრუნებს მაგის მეგობრების ლისტს. */
	public List<Friends> getFriendList(int usr_id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Friends WHERE user_id = ?")) {
			stmt.setInt(1, usr_id);
			try (ResultSet rslt = stmt.executeQuery()) {
				List<Friends> friend_list = new ArrayList<>();
				while (rslt.next()) {
					Friends friend = new Friends();
					friend.setUserId(rslt.getInt("user_id"));
					friend.setFriendId(rslt.getInt("friend_id"));
					friend_list.add(friend);
				}
				return friend_list;
			}
		}
	}
	
	/* მეგობრებში ჩავამატება/წაშლა */
	public void addFriend(Friends fr) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Friends (user_id, friend_id) "
				+ "VALUE(" + fr.getUserId() + ", " + fr.getFriendId() + ")")) {
			stmt.executeUpdate();
		}
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Friends (user_id, friend_id) "
				+ "VALUE(" + fr.getFriendId() + ", " + fr.getUserId() + ")")) {
			stmt.executeUpdate();
		}
	}
	
	public void deleteFriend(Friends fr) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Friends WHERE user_id = ? && friend_id = ?")) {
			stmt.setInt(1, fr.getUserId());
			stmt.setInt(2, fr.getFriendId());
			stmt.executeUpdate();
		}
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Friends WHERE user_id = ? && friend_id = ?")) {
			stmt.setInt(1, fr.getFriendId());
			stmt.setInt(2, fr.getUserId());
			stmt.executeUpdate();
		}
	}
	/* მეუბნება 2 ადამიანი არიან თუ არა მეგობრები. */
	public boolean isFriend(int usr_id, int friend_id) throws SQLException {
		List<Friends> friend_list = getFriendList(usr_id);
		for(int i = 0; i < friend_list.size(); i++) {
			if(friend_list.get(i).getFriendId() == friend_id)
				return true;
		}
		return false;
	}
	
	/* to ვარ მე, და ეს მეთოდი მიბრუნებს ვიღაც ტიპმა (from)-მა თუ გამომიგზავნა მეგობრობა. */ 
	/* ასევე შემიძლია გავარკვიო, გაგზავნილი მაქვს თუ არა ვიღაც ტიფთან რიქუესთი. (აქ From-ი ვარ მე.) */
	public boolean isRequested(int from, int to) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Messages WHERE u_from = ? && u_to = ? && m_type = 'friendrequest'")) {
			stmt.setInt(1, from);
			stmt.setInt(2, to);
			try (ResultSet rslt = stmt.executeQuery()) {
				if(rslt.next())
					return true;
			}
		}
		return false;
	}
	
	public void acceptRequest(User from, User to) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Messages WHERE u_from = ? && u_to = ? && m_type = 'friendrequest'")) {
			stmt.setInt(1, from.getUserId());
			stmt.setInt(2, to.getUserId());
			stmt.executeUpdate();
		}
		Friends fr = new Friends();
		fr.setUserId(to.getUserId());
		fr.setFriendId(from.getUserId());
		addFriend(fr);
	}
	/* აქ შეიძლება MessageDao-ს მეთოდი გამომეყენებინა მარა ასე უფრო მარტივია. */
	public void deleteRequest(User from, User to) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Messages WHERE u_from = ? && u_to = ? && m_type = 'friendrequest'")) {
			stmt.setInt(1, from.getUserId());
			stmt.setInt(2, to.getUserId());
			stmt.executeUpdate();
		}
	}
}