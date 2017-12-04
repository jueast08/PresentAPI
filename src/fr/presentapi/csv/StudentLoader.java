/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.csv;

public class StudentLoader extends CSVLoader{
	public StudentLoader(String filepath){
		super(filepath);
	}

	@Override
	public void load(){
		while(getParser().hasNext()){
			_insert(getParser().next());
		}
	}

	public void _insert(String[] data){
	}
}
