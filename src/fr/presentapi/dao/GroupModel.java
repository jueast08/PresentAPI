/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupModel{
	private static final String TABLE = "Groups";

	private final Connection _conn;

	public GroupModel(){
		_conn = DbConnection.getConnection();
	}

	public boolean insert(Group group){
		boolean success = true;
		String query = "INSERT INTO " + TABLE;

		if(group.getId() != Group.DEFAULT_ID){
			query += " VALUES(?, ?)";
		}
		else{
			query += "VALUES(?)";
		}

		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setString(1, group.getLabel());
			if(!stmt.execute()){
				System.err.println("Error executing query: " + query);
				return false;
			}

			_conn.commit();
		} catch(SQLException e){
			System.err.println("SQLException: ");
			success = false;
		}

		return success;
	}

	public Group find(long id){
		ResultSet res;
		Group g;
		String query = "SELECT * FROM " + TABLE +
			" WHERE groupId = ?";
		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setLong(1, id);
			res = stmt.executeQuery();
			if(!res.next()){
				return null;
			}
			g = new Group(id, res.getString("label"));
			res.close();
		}
		catch(SQLException e){
			System.err.println("SQLException: " + e);
			return null;
		}

		return g;
	}
}
