/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/12/17
 */
package fr.presentapi.dao;

import fr.presentapi.querybuilder.Pair;
import fr.presentapi.querybuilder.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Model<T>{

	protected Connection _conn;
	private final QueryBuilder _query;

	public Model(){
		_query = new QueryBuilder(this);
		_conn = DbConnection.getConnection();
	}

	public Model(Connection conn){
		_query = new QueryBuilder(this);
		_conn = conn;
	}

	public QueryBuilder builderSelect(String table, String[] attributes){
		return _query.select(table, attributes);
	}

	public Class getAttributeClass(String table, String attribute) throws SQLException{
		String c = "";
		ResultSet rs = _conn.createStatement().executeQuery("SELECT * FROM " + table + " WHERE 1=2");
		ResultSetMetaData metadata = rs.getMetaData();
		for (int i = 1; i <= metadata.getColumnCount(); i++){
			if (metadata.getColumnName(i).equals(attribute)){
				c = metadata.getColumnClassName(i);
				break;
			}
		}
		try{
			return Class.forName(c);
		} catch(ClassNotFoundException ex){
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return String.class;
	}

	public ResultSet request(QueryBuilder queryBuilder){
		PreparedStatement stmt;
		System.out.println("Query >>> " + queryBuilder.build());
		try{
			stmt = _conn.prepareStatement(queryBuilder.build());
			List<Pair<String, Object>> values = queryBuilder.getValuesToBind();
			for(int i = 0; i < values.size(); i++){
				Pair p = values.get(i);
				if (Integer.class.equals(getAttributeClass(queryBuilder.getTable(), (String)p.left()))){
					System.out.println(">>> Integer/Long");
					stmt.setLong(i + 1, (Long)p. right());
				}
				else{
					System.out.println(">>> String");
					stmt.setString(i + 1, (String)p.right());
				}
			}
			System.out.println("Full query : " + stmt);
			queryBuilder.reset();
			return stmt.executeQuery();
		} catch(SQLException e){
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		queryBuilder.reset();
		return null;
	}

	abstract public QueryBuilder select(String[] attributes);

	abstract public boolean insert(T obj);

	abstract public boolean exists(Object pk);
	//abstract public boolean get(...);
	//asbstract public boolean delete(...);
}
