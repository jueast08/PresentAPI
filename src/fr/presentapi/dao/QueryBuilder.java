/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

public class QueryBuilder{
	private String _query;
	
	public QueryBuilder selectAll(String table){
		_query = "SELECT * FROM " + table + " ";
		return this;
	}
	
	public QueryBuilder select(String table, String[] attributes){
		_query = "SELECT ";
		
		if(attributes.length == 0){
			_query += "*";
		}
		
		for(int i = 0; i < attributes.length; i++){
			_query += attributes[i];
			if(i != attributes.length - 1){
				_query += ",";
			}
		}
		_query += " FROM " + table + " ";
		return this;
	}
	
	public String build(){
		return new String(_query);
	}
}
