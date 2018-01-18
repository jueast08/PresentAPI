/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.dao;

public class Group{
	public static long DEFAULT_ID = -1;	

	private long _groupId;
	private String _name;	

	public Group(long groupId, String name){
		_groupId = groupId;
		_name = name;
	}

	public Group(String name){
		_groupId = Group.DEFAULT_ID;
		_name = name;
	}

	public long getId(){
		return _groupId;
	}

	public String getName(){
		return _name;
	}

	public String toString(){
		return "<Group Object>{id: " + _groupId + ", label: " + _name + "}";
	}

}
