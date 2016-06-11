package manager;

import java.sql.Connection;

import user.dao.FriendsDao;
import connection.DataBase;

public class FriendManager {

	protected Connection conn = null;
	private DataBase db;
	protected FriendsDao  FriendD = null;

	public FriendManager(DataBase data) throws ClassNotFoundException{
		this.db = data;
		this.conn = db.getConnection();
	}

	public FriendsDao getFriendDao(){
	   if(this.FriendD == null){
		   this.FriendD = new FriendsDao(this.conn);
	   }
	   return this.FriendD;
	}
}
