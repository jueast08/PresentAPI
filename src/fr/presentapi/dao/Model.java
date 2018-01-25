/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/12/17
 */
package fr.presentapi.dao;


/* TODO: make all models extends this class */
public abstract class Model<T>{
	private QueryBuilder _query;
	
	public Model(){
		_query = new QueryBuilder();
	}
	
	public QueryBuilder builderSelect(String table, String[] attributes){
		return _query.select(table, attributes);
	}
	
	abstract public QueryBuilder select(String[] attributes);
	abstract public boolean insert(T obj);
	abstract public boolean exists(Object pk);
	//abstract public boolean get(...);
	//asbstract public boolean delete(...);
}
