/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.csv;

import fr.presentapi.dao.BelongModel;
import fr.presentapi.dao.Group;
import fr.presentapi.dao.GroupModel;
import fr.presentapi.dao.UserModel;
import fr.presentapi.dao.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class StudentLoader extends CSVLoader{
	/* TODO: load from an existing config file or read firest line of csv with
			 fields name
	*/
	/* Fields with name and positions in the CSV file */
	private static final Map<String, Integer> _FIELDS;
	static{
		Map<String, Integer> tmpmap = new HashMap<>();
		tmpmap.put("etuid", 0);
		tmpmap.put("prenom", 1);
		tmpmap.put("nom", 2);
		tmpmap.put("email", 3);
		tmpmap.put("specialite", 4);
		tmpmap.put("majeure", 5);
		tmpmap.put("promo", 6);
		tmpmap.put("td", 7);
		tmpmap.put("tp", 8);
		_FIELDS = Collections.unmodifiableMap(tmpmap);
	}

	private UserModel _usermodel;
	private BelongModel _belongmodel;
	private GroupModel _groupmodel;

	public StudentLoader(String filepath){
		super(filepath);
		_usermodel = new UserModel();
		_belongmodel = new BelongModel();
		_groupmodel = new GroupModel();
	}

	@Override
	public int load(){
		int failedRows = 0;
		while(getParser().hasNext()){
			String[] row = getParser().next();
			_insertGroups(_createGroups(row));
			if(!_insert(row)){
				failedRows++;
			}
			_linkUserToGroups(_createUser(row), _createGroups(row));
		}
		return failedRows;
	}

	private User _createUser(String[] data){
		long statusId = 1L;
		/* TODO: get status */
		return new User(
			Long.parseLong(data[_FIELDS.get("etuid")]),
			data[_FIELDS.get("prenom")],
			data[_FIELDS.get("nom")],
			data[_FIELDS.get("email")],
			"0123456789",/* Random salt */
			statusId);
	}
	
	private boolean _insert(String[] data){
		return _usermodel.insert(_createUser(data));
	}

	public String _createGroups(String[] data){
		List<String> groups = new ArrayList<>();
		String group = 
			data[_FIELDS.get("promo")] + "_" + 
			data[_FIELDS.get("specialite")] + "_" +
			data[_FIELDS.get("majeure")] + "_" +
			data[_FIELDS.get("td")] + "_" +
			data[_FIELDS.get("tp")];

		return group;
	}
	
	public void _insertGroups(String group){
		_groupmodel.insert(new Group(group));
	}
	
	public void _linkUserToGroups(User user, String group){
		_belongmodel.insert(user, _groupmodel.find(group));
	}
}
