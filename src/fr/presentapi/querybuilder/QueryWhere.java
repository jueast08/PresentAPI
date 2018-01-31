/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.querybuilder;

public final class QueryWhere{
	private final String _attribute;
	private final String _op;
	//private final String _value;
	private final String _logic;
	private final Object _value;
	
	public QueryWhere(String attribute, Object value){
		_attribute = attribute;
		_op = "=";
		_value = value;
		_logic = "";
	}
	
	public QueryWhere(String attribute, String op, Object value){
		_attribute = attribute;
		_op = op;
		_value = value;
		_logic = "";
	}
	
	public QueryWhere(String attribute, String op, Object value, String logic){
		_attribute = attribute;
		_op = op;
		_value = value;
		_logic = logic;
	}
	
	public Object getValue(){
		return _value;
	}
	
	public String getAttribute(){
		return _attribute;
	}
	
	@Override
	public String toString(){
		//return " " + _logic + " " + _attribute + " " + _op + " '" + _value + "' ";
		String whereclause = _logic.isEmpty() ? "" : _logic + " ";
		return whereclause + _attribute.trim() + _op + "? ";
	}
}
