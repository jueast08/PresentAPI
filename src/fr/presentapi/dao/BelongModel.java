/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 07/12/17
 */

package fr.presentapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BelongModel{
	public final static String TABLE = "Belongs";
	public final static String[] FIELDS = {"numEtu", "groupId"};

	private final Connection _conn;	

	public BelongModel(){
		_conn = DbConnection.getConnection();
	}

	public boolean insert(MockUser u, Group g){
		boolean success = true;
		String query = "INSERT INTO " + TABLE + " VALUES(?, ?)";

		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setLong(1, u.getId());
			stmt.setLong(2, g.getId());
			if(!stmt.execute()){
				System.err.println("Error executing query: " + query);
				success = false;
			}
		}
		catch(SQLException e){
			System.err.println("SQLException: " + e);
			success = false;
		}

		return success;
	}

	public List<Group> findGroupForUser(long uid){
		ArrayList<Group> groups = new ArrayList<>();
		ResultSet rs;
		String query = "SELECT * FROM " + TABLE + " g " +
			"LEFT JOIN " + MockUser.TABLE + " u ON g.numEtu = u.numEtu " + 
			"WHERE numEtu = ?";

		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setLong(1, uid);
			rs = stmt.executeQuery();
			while(rs.next()){
				groups.add(new Group(
					rs.getLong("groupId"), 
					rs.getString("name")));
			}
		}
		catch(SQLException e){
			System.err.println("SQLException: " + e);
		}

		return groups;
	}

	public List<MockUser> findUserInGroup(long gid){
		ArrayList<MockUser> users = new ArrayList<>();
		ResultSet rs;
		String query = "SELECT * FROM " + TABLE +
			"WHERE groupId = ?";

		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setLong(1, gid);
			rs = stmt.executeQuery();
			while(rs.next()){
				users.add(new MockUser());
			}
		}
		catch(SQLException e){
			System.err.println("SQLException: " + e);
		}

		return users;
	}
}
