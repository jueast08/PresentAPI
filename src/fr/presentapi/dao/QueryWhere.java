/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

public final class QueryWhere{
	private final String _attribute;
	private final String _op;
	private final String _value;
	private final String _logic;
	
	public QueryWhere(String attribute, String value){
		_attribute = attribute;
		_op = "=";
		_value = value;
		_logic = "";
	}
	
	public QueryWhere(String attribute, String op, String value){
		_attribute = attribute;
		_op = op;
		_value = value;
		_logic = "";
	}
	
	public QueryWhere(String attribute, String op, String value, String logic){
		_attribute = attribute;
		_op = op;
		_value = value;
		_logic = logic;
	}
	
	@Override
	public String toString(){
		//return " " + _logic + " " + _attribute + " " + _op + " '" + _value + "' ";
		String whereclause = _logic.isEmpty() ? "" : _logic + " ";
		return whereclause + _attribute + _op + "? ";
	}
}
