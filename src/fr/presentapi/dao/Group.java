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
	private String _label;	

	public Group(long groupId, String label){
		_groupId = groupId;
		_label = label;
	}

	public Group(String label){
		_groupId = Group.DEFAULT_ID;
		_label = label;
	}

	public long getId(){
		return _groupId;
	}

	public String getLabel(){
		return _label;
	}

	public String toString(){
		return "<Group Object>{id: " + _groupId + ", label: " + _label + "}";
	}

}
