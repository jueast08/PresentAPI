/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.csv;

import fr.presentapi.dao.BelongModel;
import fr.presentapi.dao.UsersDAO;
import fr.presentapi.dao.Users;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class StudentLoader extends CSVLoader{
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

	private UsersDAO _usermodel;
	private BelongModel _belongmodel;

	public StudentLoader(String filepath){
		super(filepath);
		_usermodel = new UsersDAO();
		_belongmodel = new BelongModel();
	}

	@Override
	public int load(){
		int failedRows = 0;
		while(getParser().hasNext()){
			String[] row = getParser().next();
			if(!_insert(row)){
				failedRows++;
			}
			_createGroups(row);
		}
		return failedRows;
	}

	private boolean _insert(String[] data){
		long statusId = 1L;
		/* TODO: get status */
		/* TODO: create groups from td, tp, promo, major */
		Users u = new Users(
			Long.parseLong(data[_FIELDS.get("etuid")]),
			data[_FIELDS.get("prenom")],
			data[_FIELDS.get("nom")],
			data[_FIELDS.get("email")],
			"0123456789",/* Random salt */
			statusId
		);
		return _usermodel.insertUsers(u);
	}

	public List<String> _createGroups(String[] data){
		List<String> groups = new ArrayList<>();
		String[] tokens = {
			data[_FIELDS.get("promo")],
			data[_FIELDS.get("specialite")],
			data[_FIELDS.get("majeure")],
			data[_FIELDS.get("td")],
			data[_FIELDS.get("tp")]
		};

		for(int i = 0; i < tokens.length; i++){
			String current = "";
			for(int j = i; j < tokens.length; j++){
				current += tokens[j];
				groups.add(current);
				current += "_";
			}
		}
		
		System.out.println(Arrays.toString(groups.toArray()));
		return groups;
	}
}
