/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

public class QueryBuilder{
	private String _query;
	private boolean _whereAdded;
	
	private enum WhereLogic{
		WHERE_AND("AND"),
		WHERE_OR("OR");
		private final String _str;
		private WhereLogic(String str){
			_str = str;
		}
		@Override
		public String toString(){
			return _str;
		}
	}
	
	public enum WhereOp{
		WHERE_EQ("="),
		WHERE_NE("<>"),
		WHERE_LT("<"),
		WHERE_GT(">"),
		WHERE_LE("<="),
		WHERE_GE(">="),
		WHERE_LIKE(" LIKE ");
		private final String _str;
		private WhereOp(String value){
			_str = value;
		}
		@Override
		public String toString(){
			return _str;
		}
	}
	
	public QueryBuilder(){
		_query = "";
		_whereAdded = false;
	}
	
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
	
	public QueryBuilder where(String attribute){
		return where(attribute, WhereOp.WHERE_EQ);
	}
	
	
	public QueryBuilder where(String attribute, WhereOp op, WhereLogic logic){
		if(!_whereAdded){
			_query += "WHERE ";
			_whereAdded = true;
		}
		else{
			_query += logic + " ";
		}
		_query += attribute + op + "? ";
		return this;		
	}
	public QueryBuilder where(String attribute, WhereOp op){
		return where(attribute, op, WhereLogic.WHERE_AND);
	}
	
	public QueryBuilder orWhere(String attribute, WhereOp op){
		return where(attribute, op, WhereLogic.WHERE_OR);
	}
	
	public String build(){
		_whereAdded = false;
		return new String(_query);
	}
}
