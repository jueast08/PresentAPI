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
	public static final String TABLE = "Groups";
	public static final String[] FIELDS = {"groupId", "name"};

	private final Connection _conn;

	public GroupModel(){
		_conn = DbConnection.getConnection();
	}

	public boolean insert(Group group){
		boolean success = true;
		/* Ugly as fuck */
		String query = "INSERT INTO " + TABLE + "(name) "
			+ "SELECT ? WHERE NOT EXISTS("
			+ "SELECT 1 FROM GROUPS WHERE name = ?)";

		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setString(1, group.getName());
			stmt.setString(2, group.getName());
			if(stmt.executeUpdate() == 0){
				System.err.println("GroupsModel.java(Error executing query): " + query + " Group already exists!");
				success = false;
			}

			_conn.commit();
		} catch(SQLException e){
			System.err.println("GroupsModel.java(SQLException): " + e.getMessage());
			success = false;
		}

		return success;
	}

	public Group find(String name){
		ResultSet res;
		Group g;
		String query = "SELECT * FROM " + TABLE +
			" WHERE name = ?";
		try{
			PreparedStatement stmt = _conn.prepareStatement(query);
			stmt.setString(1, name);
			res = stmt.executeQuery();
			if(!res.next()){
				return null;
			}
			g = new Group(res.getLong("groupId"), name);
			res.close();
		}
		catch(SQLException e){
			System.err.println("GroupModel.java(SQLException): " + e);
			return null;
		}

		return g;
	}
}
