/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/12/17
 */
package fr.presentapi.querybuilder;

public class Pair<K, V>{
	private final K _key;
	private final V _value;
	
	public Pair(K key, V value){
		_key = key;
		_value = value;
	}
	
	public K left(){
		return _key;
	}
	
	public V right(){
		return _value;
	}
}
