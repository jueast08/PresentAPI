/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.querybuilder;

import fr.presentapi.dao.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilder{
	private final Model _model;
	private String _table;
	private final List<String> _attributes;
	private final List<QueryWhere> _whereData;
	
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
	
	public QueryBuilder(Model model){
		_model = model;
		_whereData = new ArrayList<>();
		_attributes = new ArrayList<>();
	}
	
	public void reset(){
		_table = "";
		_whereData.clear();
		_attributes.clear();
	}
	
	public QueryBuilder selectAll(String table){
		_table = table;
		return this;
	}
	
	public QueryBuilder select(String table, String[] attributes){
		_table = table;
		_attributes.addAll(Arrays.asList(attributes));
		return this;
	}
	
	public QueryBuilder where(String attribute, WhereOp op, Object value, WhereLogic logic){
		String l = _whereData.isEmpty() ? "" : logic.toString();
		_whereData.add(new QueryWhere(
			attribute, 
			op.toString(), 
			value, 
			l));
		return this;		
	}
	
	public QueryBuilder where(String attribute, Object value){
		return where(attribute, WhereOp.WHERE_EQ, value, WhereLogic.WHERE_AND);
	}
	
	public QueryBuilder where(String attribute, WhereOp op, Object value){
		return where(attribute, op, value, WhereLogic.WHERE_AND);
	}	
	
	public QueryBuilder orWhere(String attribute, WhereOp op, Object value){
		return where(attribute, op, value, WhereLogic.WHERE_OR);
	}
	
	public List<Pair<String, Object>> getValuesToBind(){
		List<Pair<String, Object>> values = new ArrayList<>();
		for(QueryWhere w : _whereData){
			values.add(new Pair<>(w.getAttribute(), w.getValue()));
		}
		return values;
	}
	
	public String getTable(){
		return _table;
	}
	
	public String build(){
		String query = "SELECT ";
		if(_attributes.isEmpty()){
			query += "*";
		}
		else{
			for(int i = 0; i < _attributes.size(); i++){
				query += _attributes.get(i);
				if(i != _attributes.size() - 1){
					query += ",";
				}
			}
		}
		query += " FROM " + _table + " ";
		if(!_whereData.isEmpty()){
			query += "WHERE ";
			for(QueryWhere w : _whereData){
				query += w;
			}
		}
		return query;
	}
	
	public ResultSet request() throws SQLException{
		return _model.request(this);
	}
}
